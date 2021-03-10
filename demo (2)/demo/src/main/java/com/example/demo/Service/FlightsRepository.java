package com.example.demo.Service;

import com.example.demo.Flights.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FlightsRepository
        extends JpaRepository<Flight, Long> {

    Optional<Flight> findFlightByStartDate(LocalDate startDate);
    Flight getFlightByStartDate(LocalDate startDate);
}
