package com.example.demo.Website;

import com.example.demo.Flights.Flight;
import com.example.demo.Flights.FlightController;
import com.example.demo.User.User;
import com.example.demo.User.UserController;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "main.html";
    }


    @PostMapping(value = "/test", produces = {"application/json"})
    @ResponseBody
    public String test(@RequestBody Map<String, String> params) {
        return "a";
    }

    @PostMapping("/search.html")
    public String search(@RequestParam Map<String, String> params, Model model) {
        JSONObject json = new JSONObject(params);
        model.addAttribute("flights", flightController.searchFlights(params.get("searchbar")));
        return "search.html";
    }

    @GetMapping("/search.html")
    public String searchGet(Model model) {
        model.addAttribute("flights", flightController.getAllFlight());
        return "search.html";
    }

    @GetMapping(path = "/flight/{id}")
    public String flight(@PathVariable("id") Long id, Model model) {
        Flight chosenFlight = flightController.getById(id);
        model.addAttribute("flight", chosenFlight);
        return "flight.html";
    }

    @PostMapping(path = "/flight/{id}", produces = {"application/json"})
    public String flight(@PathVariable("id") Long id, @RequestParam Map<String, String> params,Model model) throws JSONException {
        JSONObject json = new JSONObject(params);
        User user = new User(json.getString("name"),json.getString("email"));
        userController.registerNewUser(user,id);
        return flight(id,model);
    }
    @GetMapping("/brazil.html")
    public String brazil(){
        return "brazil.html";
    }

}

