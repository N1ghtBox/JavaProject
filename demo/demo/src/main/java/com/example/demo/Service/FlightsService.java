package com.example.demo.Service;

import com.example.demo.Flights.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FlightsService {
    private final FlightsRepository flightsRepository;
    Comparator<Flight> compareByRating = Comparator.comparing(Flight::getRating);

    @Autowired
    public FlightsService(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    public List<Flight> getAllFlights() {
        List<Flight> sorted = flightsRepository.findAll();
        sorted.sort(compareByRating.reversed());
        return sorted;
    }

    public void addNewFlight(Flight flight) {
        flightsRepository.save(flight);
    }

    public List<Flight> getAllFlightsByCity(String place) {
        List<Flight> allFlights = getAllFlights();
        if (place.equals("")) {
            return allFlights;
        }

        Predicate<Flight> containsCity = flight -> flight.getFlights().containsValue(StringUtils.capitalize(place));
        List<Flight> filteredFlights = allFlights.stream().filter(containsCity)
                .collect(Collectors.toList());
        return filteredFlights;
    }

    public Flight getFlightById(Long id) {
        return flightsRepository.getFlightById(id);
    }

    public void deleteFlight(Long id) {
        flightsRepository.delete(getFlightById(id));
    }

    public void editFlight(Flight flight){
        flightsRepository.save(flight);
    }
}
