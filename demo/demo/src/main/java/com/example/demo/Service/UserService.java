package com.example.demo.Service;

import com.example.demo.Email.EmailSender;
import com.example.demo.Flights.Flight;
import com.example.demo.User.User;
import com.example.demo.Website.Security.ConfirmationToken;
import com.example.demo.Website.Security.ConfirmationTokenRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    private final UserRepository userRepository;
    private final FlightsRepository flightsRepository;
    private final EmailSender emailSender;
    private final ConfirmationTokenRepository tokenRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       FlightsRepository flightsRepository,
                       EmailSender emailSender,
                       ConfirmationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.flightsRepository = flightsRepository;
        this.emailSender = emailSender;
        this.tokenRepository = tokenRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){return userRepository.findByEmailIgnoreCase(email); }

    public String addNewUser(JSONObject json, Long id) throws JSONException, InterruptedException {
        User user = new User(json.getString("name")+" "+json.getString("surName"),
                json.getString("email"), json.getInt("people"), json.getInt("days"));
        Optional<User> newUser = userRepository
                .findUserByEmail(user.getEmail());
        if (newUser.isPresent()) {
            return "/error";
        }
        Flight chosenFlight = flightsRepository.getFlightById(id);
        String[] date = json.get("date").toString().split("to");
        Hashtable <String,String> daterange = new Hashtable<String,String>();
        daterange.put("start",date[0]);
        daterange.put("end",date[1]);
        Hashtable<String, Hashtable<String, String>> map = new Hashtable<>();
        map.put("date",daterange);
        map.put("flights", chosenFlight.getFlights());
        user.setFlightInfo(map);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        userRepository.save(user);
        tokenRepository.save(confirmationToken);
        emailSender.sendEmail(user,"Confirm E-mail","To confirm your account, please click here:\n http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
        return "/confirm";
    }

    public void enableNewUser(User user,Long id) throws InterruptedException {
        userRepository.save(user);
        tokenRepository.deleteById(id);
        emailSender.sendEmail(user,"Flight ticket","a");
    }
}
