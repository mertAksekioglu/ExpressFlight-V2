package com.lunex.LunEx1.mock.mockService;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

@Service
public class SunExpressGetFlightsService {



    String resource_path = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\data\\mock\\mock_flight_data.json";

    public String getFlights() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(resource_path));
            return reader.lines().collect(Collectors.joining());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


}
