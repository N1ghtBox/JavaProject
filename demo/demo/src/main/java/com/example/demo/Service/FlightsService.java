package com.example.demo.Service;

import com.example.demo.Flights.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public List<Flight> getAllFlightsByCity(String city) {
        List<Flight> allFlights = flightsRepository.findAll();
        Predicate<Flight> containsCity = flight -> flight.getFlights().contains(city);
        List<Flight> filteredFlights = allFlights.stream().filter(containsCity)
                .collect(Collectors.toList());
        return filteredFlights;
    }

    public Flight getFlightById(Long id) {
        return flightsRepository.getFlightById(id);
    }

}
