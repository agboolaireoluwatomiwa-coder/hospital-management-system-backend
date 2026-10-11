package com.hospital.config;

import com.hospital.model.User;
import com.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner createAdmin(UserRepository users, PasswordEncoder encoder,
                                  @Value("${app.admin.username}") String username,
                                  @Value("${app.admin.password}") String password) {
        return args -> {
            if (users.count() == 0) {
                User admin = new User();
                admin.setUsername(username);
                admin.setPassword(encoder.encode(password));
                admin.setFullName("System Administrator");
                admin.setRole("ADMIN");
                users.save(admin);
            }
        };
    }
}
