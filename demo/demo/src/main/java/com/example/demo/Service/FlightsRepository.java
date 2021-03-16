package com.example.demo.Service;

import com.example.demo.Flights.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightsRepository
        extends JpaRepository<Flight, Long> {


    List<Flight> findByFlightsContaining(String city);

    Flight getFlightById(Long Id);
}
