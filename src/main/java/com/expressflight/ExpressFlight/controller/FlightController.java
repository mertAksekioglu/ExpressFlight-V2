package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.FlightSearchRequestDTO;
import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.microservice.SunExpressFlightIntegrationService;
import com.expressflight.ExpressFlight.service.FlightService;
import com.expressflight.ExpressFlight.serviceInterface.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/flight")
@CrossOrigin(origins = "*")
public class FlightController {

    private IFlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDTO> getAllFlights() {

        return flightService.getAllFlights();
    }

    @GetMapping(value = "/get-id")
    public FlightDTO getFlight(@RequestParam(value = "id") Long flightId) {
        return flightService.getFlight(flightId);
    }

    @PostMapping(value = "/search-flight")
    public List<FlightDTO> searchFlight(@RequestBody FlightSearchRequestDTO flightSearchRequestDto) {
        return flightService.searchFlight(flightSearchRequestDto);
    }

    @PostMapping(value = "/add-flight")
    public FlightDTO addFlight(@RequestBody FlightDTO flightDto) {
        return flightService.addFlight(flightDto);
    }

    @DeleteMapping(value = "/delete-id")
    public FlightDTO deleteFlight(@RequestParam(value = "id") Long flightId) {
        return flightService.deleteFlight(flightId);
    }

    @PutMapping(value = "/update-flight")
    public FlightDTO updateFlight(@RequestBody FlightDTO flightDto, @RequestParam(value = "id") Long flightId) {
        return flightService.updateFlight(flightDto, flightId);
    }

}
