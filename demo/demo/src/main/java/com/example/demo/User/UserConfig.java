//package com.example.demo.User;
//
//import com.example.demo.Service.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class UserConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository repository){
//        return args -> {
//                User Admin = new User(
//                        1L,
//                        "Admin",
//                        "Dawid Witczak",
//                        "nxo@nox.pl",
//                        null);
//                Admin.setFlightTarget(null);
//                repository.save(Admin);
//        };
//    }
//}
