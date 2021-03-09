package com.example.demo.model;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class PersonConfig {
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            User Dawid = new User(UUID.randomUUID(),
                    "Dawid Witczak");
            User Kacper = new User(UUID.randomUUID(),
                    "Kacper Giełda");
        };
    }
}
