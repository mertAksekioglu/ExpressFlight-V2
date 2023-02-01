package com.lunex.LunEx1.controller;


import com.lunex.LunEx1.dto.ConnectedFlightDTO;
import com.lunex.LunEx1.dto.FlightSearchRequestDTO;
import com.lunex.LunEx1.service.ConnectedFlightService;
import com.lunex.LunEx1.serviceInterface.IConnectedFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/connectedFlight")
@CrossOrigin(origins = "*")
public class ConnectedFlightController {


    @Autowired
    private IConnectedFlightService connectedFlightService;

    @Autowired
    public ConnectedFlightController(ConnectedFlightService connectedFlightService) {
        this.connectedFlightService = connectedFlightService;
    }

    @GetMapping
    public List<ConnectedFlightDTO> getAllPlanes() {
        return connectedFlightService.getAllConnectedFlights();
    }

    @GetMapping(value = "/get-id")
    public ConnectedFlightDTO getConnectedFlight(@RequestParam(value = "id") Long connectedFlightId) {
        return connectedFlightService.getConnectedFlight(connectedFlightId);
    }

    @PostMapping(value = "/search-connectedFlight")
    public List<ConnectedFlightDTO> searchConnectedFlight(@RequestBody FlightSearchRequestDTO connectedFlightSearchRequestDto) {
        return connectedFlightService.searchConnectedFlight(connectedFlightSearchRequestDto);
    }

    @PostMapping(value = "/add-connectedFlight")
    public ConnectedFlightDTO addConnectedFlight(@RequestBody ConnectedFlightDTO connectedFlightDto) {
        return connectedFlightService.addConnectedFlight(connectedFlightDto);
    }

    @DeleteMapping(value = "/delete-id")
    public ConnectedFlightDTO deleteConnectedFlight(@RequestParam(value = "id") Long connectedFlightId) {
        return connectedFlightService.deleteConnectedFlight(connectedFlightId);
    }

    @PutMapping(value = "/update-connectedFlight")
    public ConnectedFlightDTO updateConnectedFlight(@RequestBody ConnectedFlightDTO connectedFlightDto, @RequestParam(value = "id") Long connectedFlightId) {
        return connectedFlightService.updateConnectedFlight(connectedFlightDto, connectedFlightId);
    }




}
