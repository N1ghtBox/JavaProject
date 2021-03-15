package com.example.demo.Flights;

import com.example.demo.Service.FlightsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;

@Configuration
public class FlightConfig {

    @Bean
    CommandLineRunner commandLineRunner(FlightsRepository repository) {
        Hashtable<String, String> a = new Hashtable<String, String>();
        a.put("to", "Warszawa");
        a.put("from", "Szczecin");
        Hashtable<String, String> aa = new Hashtable<String, String>();
        aa.put("to", "Śrem");
        aa.put("from", "Lublin");
        return args -> {
            Flight b = new Flight(
                    LocalDate.parse("2021-05-01"),
                    a,
                    "Lot daleko stąd",
                    1000);
            Flight c = new Flight(
                    LocalDate.parse("2021-06-01"),
                    a,
                    "Lot daleko stąd 2",
                    2000);
            Flight d = new Flight(
                    LocalDate.parse("2021-06-01"),
                    aa,
                    "Lot daleko stąd 3",
                    3000);
            Flight e = new Flight(
                    LocalDate.parse("2021-06-01"),
                    a,
                    "Lot daleko stąd 4",
                    21);
            Flight f = new Flight(
                    LocalDate.parse("2021-06-01"),
                    aa,
                    "Lot daleko stąd 4",
                    3);
            repository.saveAll(List.of(b, c, d, e, f));
        };
    }
}
