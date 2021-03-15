package com.example.demo.Website;

import com.example.demo.Flights.Flight;
import com.example.demo.Flights.FlightController;
import com.example.demo.User.User;
import com.example.demo.User.UserController;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @GetMapping("/main.html")
    public String hello(Model model) {
        List<Flight> top4 = new ArrayList<Flight>();
        top4 = flightController.getAllFlight();
        top4 = top4.subList(0, 4);
        List<String> labels = Arrays.asList("Flights", "Parking", "For customer");
        model.addAttribute("labels", labels);
        model.addAttribute("flights", top4);
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
    public String test(@RequestBody Map<String, String> params) {
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
    @PostMapping("/search.html")
    public String search(@RequestParam Map<String, String> params, Model model){
        JSONObject json = new JSONObject(params);
        model.addAttribute("flights", flightController.searchFlights(params.get("searchbar")));
        return "search.html";
    }
}

