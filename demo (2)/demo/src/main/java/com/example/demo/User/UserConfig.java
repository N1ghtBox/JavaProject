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
//                User Dawid = new User("Dawid Witczak",
//                        "nxo@nox.pl",
//                        "User");
//                User Kacper = new User("Kacper Giełda",
//                    "nxo@nox.pl",
//                    "Admin");
//                repository.saveAll(List.of(Dawid,Kacper));
//        };
//    }
//}
