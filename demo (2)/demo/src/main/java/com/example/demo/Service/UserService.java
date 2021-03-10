package com.example.demo.Service;

import com.example.demo.Flights.Flight;
import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FlightsRepository flightsRepository;

    @Autowired
    public UserService(UserRepository userRepository, FlightsRepository flightsRepository) {
        this.userRepository = userRepository;
        this.flightsRepository = flightsRepository;
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
        String to = user.getEmail();
        String from = "witczak.dawid.2gp@gmail.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        Session session = Session.getDefaultInstance(properties);
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(from);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Bilet lotniczy");
            String tekst = String.format("Udało ci się zabookować bilet na dzień %tD",
                    user.getDate());
            message.setText(tekst);
            Transport.send(message);

        }catch(MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
