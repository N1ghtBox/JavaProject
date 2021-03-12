package com.example.demo.Website;

import com.example.demo.Flights.FlightController;
import com.example.demo.User.User;
import com.example.demo.User.UserController;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class MainPage {
    private final UserController userController;
    private final FlightController flightController;

    @Autowired
    public MainPage(UserController userController,
                    FlightController flightController) {
        this.userController = userController;
        this.flightController = flightController;
    }

    @RequestMapping("/")
    public String helloAdmin() {
        return "hello admin";
    }

    @GetMapping("/main.html")
    public String hello(Model model) {
        model.addAttribute("flights", flightController.getAllFlight());
        return "main.html";
    }

    @RequestMapping("/login.html")
    public String login() {
        return "login.html";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    @PostMapping(value = "/test", produces = {"application/json"})
    @ResponseBody
    public String test(@RequestParam Map<String, String> params) {
        JSONObject json = new JSONObject(params);
        User user = new User(
                params.get("name"),
                params.get("email"),
                LocalDate.parse(params.get("date")),
                params.get("type")
        );
        userController.registerNewUser(user);
        return "hello.html";
    }

}

