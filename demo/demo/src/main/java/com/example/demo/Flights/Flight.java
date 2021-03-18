package com.example.demo.Flights;

import javax.persistence.*;
import java.io.File;
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
    private Hashtable<String, String> flights;
    private String description;
    private Integer price;
    private Integer rating;

    public Flight(Hashtable<String, String> flights, String description, Integer price, Integer rating) {
        this.flights = flights;
        this.description = description;
        this.price = price;
        this.rating = rating;
    }

    public Flight() {
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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

    public String getImagePath() {

        String path = "/css/images/"+getId()+"/";
        File directory = new File("src/main/resources/static"+path);
        String[] array = directory.list();
        if(array==null) return "/css/images/img.jpg";
        return path+array[0];
    }
}
