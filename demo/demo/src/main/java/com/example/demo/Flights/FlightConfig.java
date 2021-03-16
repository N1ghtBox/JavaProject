package com.example.demo.Flights;

import com.example.demo.Service.FlightsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return args -> {
            Flight b = new Flight(
                    LocalDateTime.parse("2021-05-01 20:00",formatter),
                    a,
                    "Lot daleko stąd",
                    1000,
                    1);
            Flight c = new Flight(
                    LocalDateTime.parse("2021-06-01 21:00",formatter),
                    a,
                    "Lot daleko stąd 2",
                    2000, 1);
            Flight d = new Flight(
                    LocalDateTime.parse("2021-06-01 07:30",formatter),
                    aa,
                    "Lot daleko stąd 3",
                    3000, 3);
            Flight e = new Flight(
                    LocalDateTime.parse("2021-06-01 13:00",formatter),
                    a,
                    "Lot daleko stąd 4",
                    21, 4);
            Flight f = new Flight(
                    LocalDateTime.parse("2021-06-01 07:00",formatter),
                    aa,
                    "Lot daleko stąd 4",
                    3, 2);
            repository.saveAll(List.of(b, c, d, e, f));
        };
    }
}
