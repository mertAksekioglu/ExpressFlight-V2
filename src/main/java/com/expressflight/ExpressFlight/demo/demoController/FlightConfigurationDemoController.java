package com.expressflight.ExpressFlight.demo.demoController;

import com.expressflight.ExpressFlight.demo.demoService.FlightConfigurationDemoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/demo/configureFlights")
@CrossOrigin(origins = "*")
public class FlightConfigurationDemoController {

    FlightConfigurationDemoService flightConfigurationDemoService;

    public FlightConfigurationDemoController(FlightConfigurationDemoService flightConfigurationDemoService) {
        this.flightConfigurationDemoService = flightConfigurationDemoService;
    }

    @GetMapping
    public void configureAllFlightSeats() {
        flightConfigurationDemoService.configureAllFlightSeats();
    }

    @GetMapping(value = "/configure-id")
    public void configureFlightSeats(@RequestParam(value = "id") Long flightId) {
        flightConfigurationDemoService.configureFlightSeats(flightId);
    }

}