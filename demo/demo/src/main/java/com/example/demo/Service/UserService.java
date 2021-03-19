package com.example.demo.Service;

import com.example.demo.Email.EmailSender;
import com.example.demo.Flights.Flight;
import com.example.demo.User.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

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

    public void addNewUser(JSONObject json, Long id) throws JSONException {
        User user = new User(json.getString("name")+" "+json.getString("surName"),
                json.getString("email"), json.getInt("people"), json.getInt("days"));
        Optional<User> newUser = userRepository
                .findUserByEmail(user.getEmail());
        if (newUser.isPresent()) {
            throw new IllegalStateException("This email is taken");
        }
        Flight chosenFlight = flightsRepository.getFlightById(id);
        String[] a = json.get("date").toString().split("to");
        Hashtable <String,String> dateRange = new Hashtable<>();
        dateRange.put("start",a[0]);
        dateRange.put("end",a[1]);
        Hashtable<String, Hashtable<String, String>> map = new Hashtable<>();
        map.put("date",dateRange);
        map.put("flights", chosenFlight.getFlights());
        user.setFlightInfo(map);
        userRepository.save(user);

//        emailSender.setUser(user);
//        emailSender.start();
    }

}
