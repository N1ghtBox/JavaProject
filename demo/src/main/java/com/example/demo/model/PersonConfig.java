package com.example.demo.model;

import com.example.demo.service.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User Dawid = new User(
                    "Dawid Witczak");
            User Kacper = new User(
                    "Kacper Giełda",
                    "Admin");
            User Janek = new User(
                    "Janek Dobosz"
            );

            repository.saveAll(List.of(Dawid,Kacper,Janek));
        };
    }
}
