package com.example.demo.User;

import javax.persistence.*;
import java.util.Hashtable;
import java.util.Map;


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
    private Hashtable<String, Hashtable<String, String>> flightInfo = new Hashtable<>();

    public User() {
    }

    public User(String name,
                String email,
                Hashtable<String, Hashtable<String, String>> flightInfo) {
        this.name = name;
        this.email = email;
        this.flightInfo = flightInfo;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(Long id, String name, String email, Hashtable<String, Hashtable<String, String>> flightInfo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.flightInfo = flightInfo;
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

    public Hashtable<String, Hashtable<String,String>> getFlightInfo() {
        return flightInfo;
    }

    public void setFlightInfo(Hashtable<String, Hashtable<String,String>> flightInfo) {
        this.flightInfo = flightInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", flightInfo=" + flightInfo +
                '}';
    }
}
