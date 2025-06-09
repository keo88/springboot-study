package com.keokim.ncphw.migration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Standalone password migration script that can be run independently.
 * This script connects directly to the database and migrates passwords.
 * Usage:
 * 1. Update the database connection parameters below
 * 2. Compile and run this class: java StandalonePasswordMigration
 */
public class StandalonePasswordMigration {
    
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";
    
    public static void main(String[] args) {
        StandalonePasswordMigration migration = new StandalonePasswordMigration();
        migration.runMigration();
    }
    
    public void runMigration() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to database successfully!");
            System.out.println("Starting password migration...");
            
            List<MemberData> members = getAllMembers(connection);
            int migrated = 0;
            int skipped = 0;
            
            for (MemberData member : members) {
                if (isPasswordAlreadyHashed(member.password)) {
                    System.out.println("Skipping user " + member.username + " - password already hashed");
                    skipped++;
                    continue;
                }
                
                String hashedPassword = passwordEncoder.encode(member.password);
                
                updatePassword(connection, member.userId, hashedPassword);
                
                System.out.println("Migrated password for user: " + member.username);
                migrated++;
            }
            
            System.out.println("\nPassword migration completed!");
            System.out.println("Migrated: " + migrated + " users");
            System.out.println("Skipped: " + skipped + " users (already hashed)");
            System.out.println("Total: " + members.size() + " users");
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
    
    private List<MemberData> getAllMembers(Connection connection) throws SQLException {
        List<MemberData> members = new ArrayList<>();
        String sql = "SELECT user_id, username, password FROM member";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                MemberData member = new MemberData();
                member.userId = rs.getLong("user_id");
                member.username = rs.getString("username");
                member.password = rs.getString("password");
                members.add(member);
            }
        }
        
        return members;
    }
    
    private boolean isPasswordAlreadyHashed(String password) {
        return password != null && 
               (password.startsWith("$2a$") || 
                password.startsWith("$2b$") || 
                password.startsWith("$2y$"));
    }
    
    private void updatePassword(Connection connection, Long userId, String hashedPassword) throws SQLException {
        String sql = "UPDATE member SET password = ? WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hashedPassword);
            stmt.setLong(2, userId);
            stmt.executeUpdate();
        }
    }
    
    private static class MemberData {
        Long userId;
        String username;
        String password;
    }
} 