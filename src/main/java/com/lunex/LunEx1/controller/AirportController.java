package com.lunex.LunEx1.controller;

import com.lunex.LunEx1.domain.Airport;
import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.serviceInterface.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(path = "api/v1/airport")
@CrossOrigin(origins = "http://localhost:3000")
public class AirportController {

    @Autowired
    private IAirportService airportService;

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping(value = "/get-id")
    public Airport getAirport(@RequestParam(value = "id") Long airportId) {
        return airportService.getAirport(airportId);
    }

    @PostMapping(value = "add-airport")
    public void addAirport(@RequestBody Airport airport) {
        airportService.addAirport(airport);
    }

    @DeleteMapping(value = "/delete-id")
    public void deleteAirport(@RequestParam(value = "id") Long airportId) {
        airportService.deleteAirport(airportId);
    }

    @PutMapping(value = "/update-airport")
    public void updateAirport(@RequestBody Airport airport) {
        airportService.updateAirport(airport);
    }


}
