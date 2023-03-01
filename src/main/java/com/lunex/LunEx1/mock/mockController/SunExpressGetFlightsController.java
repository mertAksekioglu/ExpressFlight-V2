package com.lunex.LunEx1.mock.mockController;

import com.lunex.LunEx1.mock.mockService.SunExpressGetFlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/mock/flight")
@CrossOrigin(origins = "*")
public class SunExpressGetFlightsController {

    @Autowired
    SunExpressGetFlightsService sunExpressGetFlightsService;

    @GetMapping
    public String getFlights() {
        return sunExpressGetFlightsService.getFlights();
    }


}
