package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final UserRepository userRepository;

    @Autowired
    public PersonService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void addNewUser(User user,UserRepository userRepository) {
        userRepository.saveAll(List.of(user));
    }


    public List<User> getAllPeople() {
        return userRepository.findAll();
    }

}
