package com.example.demo.User;

import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "hello")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    public void registerNewUser(User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "/{id}/{idToDelete}")
    public void deleteUser(@PathVariable("id") Long id, @PathVariable("idToDelete") Long idToDelete) {
        userService.deleteUser(id, idToDelete);
    }
}
