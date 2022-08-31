package com.lunex.LunEx1.controller;

import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.service.FlightService;
import com.lunex.LunEx1.service.PlaneService;
import com.lunex.LunEx1.serviceInterface.IFlightService;
import com.lunex.LunEx1.serviceInterface.IPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/flight")
public class FlightController {


    @Autowired
    private IFlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllPlanes() {
        return flightService.getAllFlights();
    }

    @GetMapping(value = "/get-id")
    public Flight getFlight(@RequestParam(value = "id") Long flightId) {
        return flightService.getFlight(flightId);
    }

    @PostMapping(value = "/add-flight")
    public void addFlight(@RequestBody Flight flight) {
        flightService.addFlight(flight);
    }

    @DeleteMapping(value = "/delete-id")
    public void deleteFlight(@RequestParam(value = "id") Long flightId) {
        flightService.deleteFlight(flightId);
    }

    @PutMapping(value = "/update-flight")
    public void updateFlight(@RequestBody Flight flight) {
        flightService.updateFlight(flight);
    }




}
