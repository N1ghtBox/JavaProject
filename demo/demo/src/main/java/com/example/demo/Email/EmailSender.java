package com.example.demo.Email;

import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender extends Thread {

    private final JavaMailSender javaMailSender;
    private User user;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Bilety lotniczne");
        String contentOfMessage = String.format("Witaj %s\nUdało ci się zarejestrować bilety na dzień %tD\n" +
                        "Lot będzie startował z %s i będzie lądował w %s\nPozdrawiamy\nhttps://www.youtube.com/watch?v=dQw4w9WgXcQ\n" +
                        "Po więcej informacji prosimy o kontakt: witczak.dawid.2gp@gmail.com :)",
                user.getName(), user.getDate(), user.getFlightTarget().get("from"), user.getFlightTarget().get("to"));
        message.setText(contentOfMessage);

        javaMailSender.send(message);

    }

    public void run() {
        sendEmail();
        System.out.println("Email sent successfully...");
    }
}
