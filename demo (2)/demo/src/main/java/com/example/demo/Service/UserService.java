package com.example.demo.Service;

import com.example.demo.Flights.Flight;
import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FlightsRepository flightsRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public UserService(UserRepository userRepository,
                       FlightsRepository flightsRepository,
                       JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.flightsRepository = flightsRepository;
        this.javaMailSender = javaMailSender;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<Flight> flightOptional = flightsRepository
                .findFlightByStartDate(user.getDate());
        if (flightOptional.isEmpty()) {
            throw new IllegalStateException("Taki lot nie istnieje");
        }
        userRepository.save(user);
        sendEmail(user);
    }

    public void deleteUser(Long idAdmin, Long idToDelete) {
        User admin = userRepository
                .findUserById(idAdmin);
        if (!admin.getType().equals("Admin")) {
            throw new IllegalStateException("Nie posiadasz uprawnień");
        }
        userRepository.delete(userRepository.findUserById(idToDelete));
    }

    public void sendEmail(User user) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Bilety lotniczne");
        String tekst = String.format("Witaj %s\nUdało ci się zarejestrować bilety na dzień %tD",user.getName(),user.getDate());
        msg.setText(tekst);

        javaMailSender.send(msg);

    }
}
