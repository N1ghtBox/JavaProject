package com.example.demo.Flights;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate startDate;
    private String fromCity;
    private String toCity;

    public Flight(LocalDate startDate, String from, String to) {
        this.startDate = startDate;
        this.fromCity = from;
        this.toCity = to;
    }

    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getFrom() {
        return fromCity;
    }

    public void setFrom(String from) {
        this.fromCity = from;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
}
