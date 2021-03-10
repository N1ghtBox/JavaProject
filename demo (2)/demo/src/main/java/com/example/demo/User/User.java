package com.example.demo.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Hashtable;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate date;
    private String type;
    private Hashtable<String,String> flightTarget = new Hashtable<String, String>();

    public void setFlightTarget(Hashtable<String, String> flightTarget) {
        this.flightTarget = flightTarget;
    }

    public Hashtable<String, String> getFlightTarget() {
        return flightTarget;
    }

    public User() {
    }

    public User(String name,
                String email,
                LocalDate date,
                String type) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.type = type;
    }

    public User(Long id,
                String type,
                String name,
                String email,
                LocalDate date) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.email = email;
        this.date = null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setType(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", type='" + date + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
