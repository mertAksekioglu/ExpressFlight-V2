package com.lunex.LunEx1.controller;

import com.lunex.LunEx1.domain.Airport;
import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.dto.AirportDTO;
import com.lunex.LunEx1.serviceInterface.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping(path = "api/v1/airport")
@CrossOrigin(origins = "*")
public class AirportController {

    @Autowired
    private IAirportService airportService;

    @GetMapping
    public List<AirportDTO> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping(value = "/get-id")
    public AirportDTO getAirport(@RequestParam(value = "id") Long airportId) {
        return airportService.getAirport(airportId);
    }

    @GetMapping(value = "/get-code")
    public AirportDTO getAirportByCode(@RequestParam(value = "code") String airportCode) {
        return airportService.getAirportByCode(airportCode);
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
