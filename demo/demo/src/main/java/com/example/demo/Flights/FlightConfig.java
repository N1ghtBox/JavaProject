package com.example.demo.Flights;

import com.example.demo.Service.FlightsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Hashtable;
import java.util.List;

@Configuration
public class FlightConfig {

    @Bean
    CommandLineRunner commandLineRunner(FlightsRepository repository) {
        Hashtable<String, String> a = new Hashtable<String, String>();
        a.put("to", "Warszawa");
        a.put("hotel", "Szczecin");
        Hashtable<String, String> aa = new Hashtable<String, String>();
        aa.put("to", "Śrem");
        aa.put("hotel", "Lublin");
        return args -> {
            Flight b = new Flight(
                    a,
                    "Lot daleko stąd",
                    1000,
                    1);
            Flight c = new Flight(
                    a,
                    "Lot daleko stąd 2",
                    2000, 1);
            Flight d = new Flight(
                    aa,
                    "Lot daleko stąd 3",
                    3000, 3);
            Flight e = new Flight(
                    a,
                    "Lot daleko stąd 4",
                    21, 4);
            Flight f = new Flight(
                    aa,
                    "Lot daleko stąd 4",
                    3, 2);
            repository.saveAll(List.of(b, c, d, e, f));
        };
    }
}
