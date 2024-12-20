package com.tis2.AppRh.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tis2.AppRh.entities.User;
import com.tis2.AppRh.entities.enums.Role;
import com.tis2.AppRh.repositories.UserRepository;

@Configuration
public class AdminInitializer {

    @Bean
    public CommandLineRunner initializeAdmin(UserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.findByEmail("admin@example.com").isEmpty()) {
                User admin = new User();
                admin.setName("Admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123")); // Defina uma senha segura
                admin.setRole(Role.ADMIN); // Define o papel como ADMIN

                repository.save(admin);
                System.out.println("Admin user created with email: admin@example.com");
            }
        };
    }
}