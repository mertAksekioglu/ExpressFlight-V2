package com.lunex.LunEx1.config;


import com.google.gson.Gson;
import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.repository.IFlightRepository;
import com.lunex.LunEx1.repository.IPlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Configuration
@Service
public class FlightConfig {


    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\flight_data.json";

    @Autowired
    Gson gson = new Gson();

    private Flight[] flights;


    {
        try (FileReader reader = new FileReader(DATA_PATH)) {
            flights = gson.fromJson(reader, Flight[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    CommandLineRunner commandLineRunner2(IFlightRepository repository) {
        return args -> {
            repository.saveAll(List.of(flights));
        };
    }



}
