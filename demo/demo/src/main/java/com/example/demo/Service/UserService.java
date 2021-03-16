package com.example.demo.Service;

import com.example.demo.Email.EmailSender;
import com.example.demo.Flights.Flight;
import com.example.demo.User.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FlightsRepository flightsRepository;
    private final EmailSender emailSender;

    @Autowired
    public UserService(UserRepository userRepository,
                       FlightsRepository flightsRepository,
                       EmailSender emailSender) {
        this.userRepository = userRepository;
        this.flightsRepository = flightsRepository;
        this.emailSender = emailSender;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user, Long id) {
        Gson gson = new Gson();
        Optional<User> newUser = userRepository
                .findUserByEmail(user.getEmail());
        if (newUser.isPresent()) {
            throw new IllegalStateException("This email is taken");
        }
        Flight chosenFlight = flightsRepository.getFlightById(id);
        LocalDateTime time =chosenFlight.getStartDate();
        Hashtable<String,String> date = new Hashtable<>();
        date.put("year",String.valueOf(time.getYear()));
        date.put("month",String.valueOf(time.getMonth()));
        date.put("day",String.valueOf(time.getDayOfMonth()));
        Hashtable<String, Hashtable<String,String>> map = new Hashtable<>();
        map.put("startDate",date);
        map.put("flights",chosenFlight.getFlights());
        user.setFlightInfo(map);
        userRepository.save(user);

//        emailSender.setUser(user);
//        emailSender.start();
    }

}
