package com.expressflight.ExpressFlight.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
public class SunExpressFlightProvider implements IProvider {

    String apiUrl = "http://localhost:8080/api/mock/flight";
    String jsonString;

    private RestTemplate restTemplate;

    public SunExpressFlightProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getData() {
        System.out.println("a");
       jsonString = restTemplate.getForObject(apiUrl, String.class);
       return jsonString;
    }

}
