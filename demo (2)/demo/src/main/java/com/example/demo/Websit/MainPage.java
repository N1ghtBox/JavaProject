package com.example.demo.Websit;

import com.example.demo.User.User;
import com.example.demo.User.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
@Controller
public class MainPage {
    private final UserController userController;

    @Autowired
    public MainPage(UserController userController) {
        this.userController = userController;
    }


    @GetMapping("/main")
    public String hello(Model model) {
        return "hello";
    }

    @PostMapping("/main/test")
    @ResponseBody
    public String test(@RequestBody Map<String,String> params) {
        System.out.println(params);
        return "hello";
    }

}
