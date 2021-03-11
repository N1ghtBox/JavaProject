package com.example.demo.Service;

import com.example.demo.Email.EmailSender;
import com.example.demo.Flights.Flight;
import com.example.demo.User.User;
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

    public void addNewUser(User user) {
        Optional<User> newUser = userRepository
                .findUserByEmail(user.getEmail());
        if (newUser.isPresent()) {
            throw new IllegalStateException("This email was taken");
        }
        Optional<Flight> flightOptional = flightsRepository
                .findFlightByStartDate(user.getDate());
        if (flightOptional.isEmpty()) {
            throw new IllegalStateException("Flight doesn't exist");
        }
        Flight chosenFlight = flightsRepository.getFlightByStartDate(user.getDate());
        Hashtable<String, String> direction = new Hashtable<String, String>();
        direction.put("to", chosenFlight.getToCity());
        direction.put("from", chosenFlight.getFromCity());
        user.setFlightTarget(direction);
        userRepository.save(user);

//        emailSender.setUser(user);
//        emailSender.start();
    }

    public void deleteUser(Long idAdmin, Long idToDelete) {
        User admin = userRepository
                .findUserById(idAdmin);
        if (!admin.getType().equals("Admin")) {
            throw new IllegalStateException("You don't have permissions");
        }
        userRepository.delete(userRepository.findUserById(idToDelete));
    }

}
