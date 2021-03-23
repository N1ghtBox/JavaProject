package com.example.demo.User;

import com.example.demo.Service.UserService;
import com.example.demo.Website.Security.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RestController
public class UserController {
    private final UserService userService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public UserController(UserService userService,
                          ConfirmationTokenRepository confirmationTokenRepository) {
        this.userService = userService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.getUsers();
    }

}
