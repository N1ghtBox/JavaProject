package com.example.demo.Flights;

import com.example.demo.Service.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "flight")
@RestController
public class FlightController {
    private final FlightsService flightsService;

    @Autowired
    public FlightController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @GetMapping
    public List<Flight> getAllFlight() {
        return flightsService.getAllFlights();
    }

    @PostMapping
    public void addNewFlight(@RequestBody Flight flight) {
        flightsService.addNewFlight(flight);
    }

    public List<Flight> searchFlights(String city) {
        return flightsService.getAllFlightsByCity(city);
    }
}
