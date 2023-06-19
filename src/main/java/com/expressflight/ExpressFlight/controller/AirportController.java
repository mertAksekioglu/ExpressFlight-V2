package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.AirportDTO;
import com.expressflight.ExpressFlight.serviceInterface.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(path = "api/v1/airport")
@CrossOrigin(origins = "*")
public class AirportController {

    private IAirportService airportService;

    public AirportController(IAirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<AirportDTO> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping(value = "/get-id")
    public AirportDTO getAirport(@RequestParam(value = "id") Long airportId) {
        return airportService.getAirport(airportId);
    }

    @GetMapping(value = "/get-code")
    public AirportDTO getAirportByCode(@RequestParam(value = "code") String airportCodeIATA) {
        return airportService.getAirportByCodeIATA(airportCodeIATA);
    }

    @PostMapping(value = "add-airport")
    public AirportDTO addAirport(@RequestBody AirportDTO airportDto) {
        return airportService.addAirport(airportDto);
    }

    @DeleteMapping(value = "/delete-id")
    public AirportDTO deleteAirport(@RequestParam(value = "id") Long airportId) {
        return airportService.deleteAirport(airportId);
    }

    @PutMapping(value = "/update-airport")
    public AirportDTO updateAirport(@RequestBody AirportDTO airportDto, @RequestParam(value = "id") Long airportId) {
        return airportService.updateAirport(airportDto, airportId);
    }

}
