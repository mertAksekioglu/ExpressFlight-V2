package com.lunex.LunEx1.config;

import com.google.gson.Gson;
import com.lunex.LunEx1.domain.Airport;
import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.repository.IAirportRepository;
import com.lunex.LunEx1.repository.IPlaneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Configuration
public class AirportConfig {

    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\airport_data.json";

    @Autowired
    Gson gson = new Gson();

    private Airport[] airports;


    {
        try (FileReader reader = new FileReader(DATA_PATH)) {
            airports = gson.fromJson(reader, Airport[].class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    CommandLineRunner commandLineRunner35(IAirportRepository repository) {
        return args -> {

            repository.saveAll(List.of(airports));
        };
    }
}
