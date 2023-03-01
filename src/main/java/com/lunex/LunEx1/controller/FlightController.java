package com.lunex.LunEx1.controller;

import com.lunex.LunEx1.dto.FlightDTO;
import com.lunex.LunEx1.dto.FlightSearchRequestDTO;
import com.lunex.LunEx1.service.FlightService;
import com.lunex.LunEx1.serviceInterface.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/flight")
@CrossOrigin(origins = "*")
public class FlightController {


    @Autowired
    private IFlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDTO> getAllPlanes() {
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
