package com.expressflight.ExpressFlight.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
@Component
public class SunExpressFlightProvider implements IProvider {


    String apiUrl = "http://localhost:8080/api/mock/flight";
    String jsonString;

    @Autowired
    private RestTemplate restTemplate;



    @Override
    public String getData() {
       jsonString = restTemplate.getForObject(apiUrl, String.class);

       return jsonString;
    }
}
