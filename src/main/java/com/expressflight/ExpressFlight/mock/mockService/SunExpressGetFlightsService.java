package com.expressflight.ExpressFlight.mock.mockService;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

@Service
public class SunExpressGetFlightsService {

    String resource_path = "src/main/resources/data/mock/mock_flight_data.json";

    public String getFlights() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(resource_path));
            return reader.lines().collect(Collectors.joining());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
