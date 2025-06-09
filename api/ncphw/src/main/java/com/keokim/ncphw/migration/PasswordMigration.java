package com.keokim.ncphw.migration;

import com.keokim.ncphw.domain.Member;
import com.keokim.ncphw.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * One-time migration script to convert plain text passwords to BCrypt hashes.
 * This should be run once after switching from NoOpPasswordEncoder to BCryptPasswordEncoder.
 * To run this migration:
 * 1. Uncomment the @Component annotation
 * 2. Start the application
 * 3. Comment out the @Component annotation again to prevent re-running
 */
// @Component
public class PasswordMigration implements CommandLineRunner {
    
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JdbcTemplate jdbcTemplate;
    
    public PasswordMigration(MemberRepository memberRepository, 
                           PasswordEncoder passwordEncoder,
                           JdbcTemplate jdbcTemplate) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting password migration...");
        
        // Get all members
        List<Member> members = memberRepository.findAll();
        int migrated = 0;
        int skipped = 0;
        
        for (Member member : members) {
            String currentPassword = member.getPassword();
            
            // Check if password is already hashed (BCrypt hashes start with $2a$, $2b$, or $2y$)
            if (currentPassword != null && 
                (currentPassword.startsWith("$2a$") || 
                 currentPassword.startsWith("$2b$") || 
                 currentPassword.startsWith("$2y$"))) {
                System.out.println("Skipping user " + member.getUsername() + " - password already hashed");
                skipped++;
                continue;
            }
            
            // Hash the plain text password
            String hashedPassword = passwordEncoder.encode(currentPassword);
            
            // Update the password in database
            String sql = "UPDATE member SET password = ? WHERE user_id = ?";
            jdbcTemplate.update(sql, hashedPassword, member.getUserId());
            
            System.out.println("Migrated password for user: " + member.getUsername());
            migrated++;
        }
        
        System.out.println("Password migration completed!");
        System.out.println("Migrated: " + migrated + " users");
        System.out.println("Skipped: " + skipped + " users (already hashed)");
        System.out.println("Total: " + members.size() + " users");
        
        System.out.println("\n*** IMPORTANT: Remember to comment out the @Component annotation ***");
        System.out.println("*** to prevent this migration from running again! ***");
    }
} 