package com.example.demo.Service;

import com.example.demo.Flights.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsService {
    private final FlightsRepository flightsRepository;

    @Autowired
    public FlightsService(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    public List<Flight> getAllFlights() {
        return flightsRepository.findAll();
    }

    public void addNewFlight(Flight flight) {
        flightsRepository.save(flight);
    }
}
