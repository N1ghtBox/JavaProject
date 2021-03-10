//package com.example.demo.Email;
//
//import com.example.demo.User.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//public class EmailSender {
//
//
//
//    public void sendEmail(User user) {
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(user.getEmail());
//
//        msg.setSubject("Testing from Spring Boot");
//        msg.setText("Hello World \n Spring Boot Email");
//
//        javaMailSender.send(msg);
//
//    }
//}
