# Password Migration Scripts

This directory contains scripts to migrate plain text passwords to BCrypt hashes after switching from `NoOpPasswordEncoder` to `BCryptPasswordEncoder`.

## Important Notes

⚠️ **BACKUP YOUR DATABASE BEFORE RUNNING ANY MIGRATION!**

⚠️ **These scripts should be run only once after switching to BCryptPasswordEncoder**

## Option 1: Spring Boot Integration (Recommended)

Use `PasswordMigration.java` - runs as part of your Spring Boot application startup.

### Steps:
1. **Uncomment** the `@Component` annotation in `PasswordMigration.java`:
   ```java
   @Component  // <- Uncomment this line
   public class PasswordMigration implements CommandLineRunner {
   // ...
   }
   ```

2. **Start your Spring Boot application**:
   ```bash
   ./gradlew :api:ncphw:bootRun
   ```

3. **Watch the console output** for migration progress:
   ```
   Starting password migration...
   Migrated password for user: john_doe
   Migrated password for user: jane_smith
   Password migration completed!
   Migrated: 2 users
   Skipped: 0 users (already hashed)
   Total: 2 users
   ```

4. **Comment out** the `@Component` annotation again to prevent re-running:
   ```java
   // @Component  // <- Comment this out again
   public class PasswordMigration implements CommandLineRunner {
   // ...
   }
   ```

### Features:
- ✅ Uses your existing Spring configuration
- ✅ Automatically detects already-hashed passwords
- ✅ Safe to run multiple times (skips already hashed passwords)
- ✅ Detailed logging of migration progress

## Option 2: Standalone Script

Use `StandalonePasswordMigration.java` - runs independently without starting the full application.

### Steps:
1. **Update database connection parameters** in `StandalonePasswordMigration.java`:
   ```java
   private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database";
   private static final String DB_USER = "your_username";
   private static final String DB_PASSWORD = "your_password";
   ```

2. **Compile and run**:
   ```bash
   cd api/ncphw/src/main/java
   javac -cp "path/to/spring-security-crypto.jar:path/to/postgresql.jar" com/keokim/ncphw/migration/StandalonePasswordMigration.java
   java -cp ".:path/to/spring-security-crypto.jar:path/to/postgresql.jar" com.keokim.ncphw.migration.StandalonePasswordMigration
   ```

### Features:
- ✅ No need to start the full application
- ✅ Direct database connection
- ✅ Automatically detects already-hashed passwords
- ✅ Safe to run multiple times

## What the Migration Does

1. **Fetches all users** from the `member` table
2. **Checks each password** to see if it's already BCrypt hashed (starts with `$2a$`, `$2b$`, or `$2y$`)
3. **Skips already hashed passwords** to prevent double-hashing
4. **Hashes plain text passwords** using BCryptPasswordEncoder
5. **Updates the database** with the new hashed passwords
6. **Provides detailed logging** of the migration process

## Password Hash Detection

The migration automatically detects BCrypt hashes by checking if passwords start with:
- `$2a$` (BCrypt)
- `$2b$` (BCrypt)
- `$2y$` (BCrypt)

This ensures the migration is **safe to run multiple times** without corrupting already-hashed passwords.

## After Migration

Once the migration is complete:
1. All existing users can log in with their original passwords
2. New users will have their passwords automatically hashed
3. The security of your application is significantly improved

## Troubleshooting

### Common Issues:
- **Database connection errors**: Check your database URL, username, and password
- **Permission errors**: Ensure the database user has UPDATE permissions on the member table
- **Dependency errors**: Make sure Spring Security and database driver dependencies are available

### Rollback:
If you need to rollback, you'll need to restore from your database backup, as BCrypt hashing is irreversible. 