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
    private Integer Price;

    public Flight(LocalDate startDate, String fromCity, String toCity, Integer price) {
        this.startDate = startDate;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.Price = price;
    }

    public Flight() {
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
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

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
}
