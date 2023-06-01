package com.expressflight.ExpressFlight.mock.mockController;

import com.expressflight.ExpressFlight.mock.mockService.SunExpressGetFlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/mock/flight")
@CrossOrigin(origins = "*")
public class SunExpressGetFlightsController {


    SunExpressGetFlightsService sunExpressGetFlightsService;

    public SunExpressGetFlightsController(SunExpressGetFlightsService sunExpressGetFlightsService) {
        this.sunExpressGetFlightsService = sunExpressGetFlightsService;
    }

    @GetMapping
    public String getFlights() {
        return sunExpressGetFlightsService.getFlights();
    }


}
