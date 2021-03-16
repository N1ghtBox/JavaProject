package com.example.demo.Flights;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Hashtable;

@Entity
@Table
public class Flight {

    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_sequence"
    )
    private Long id;
    private LocalDateTime startDate;
    private Hashtable<String, String> flights;
    private String description;
    private Integer price;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    private Integer rating;

    public Flight(LocalDateTime startDate, Hashtable<String, String> flights, String description, Integer price, Integer rating) {
        this.startDate = startDate;
        this.flights = flights;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    public Flight() {
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Hashtable<String, String> getFlights() {
        return flights;
    }

    public void setFlights(Hashtable<String, String> flights) {
        this.flights = flights;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

}
