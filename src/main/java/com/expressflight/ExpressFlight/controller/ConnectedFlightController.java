package com.expressflight.ExpressFlight.controller;


import com.expressflight.ExpressFlight.dto.ConnectedFlightDTO;
import com.expressflight.ExpressFlight.requestdto.ConnectedFlightRequestDTO;
import com.expressflight.ExpressFlight.requestdto.FlightSearchRequestDTO;
import com.expressflight.ExpressFlight.serviceInterface.IConnectedFlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/connectedFlight")
@CrossOrigin(origins = "*")
public class ConnectedFlightController {

    private IConnectedFlightService connectedFlightService;

    public ConnectedFlightController(IConnectedFlightService connectedFlightService) {
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
    public ConnectedFlightDTO addConnectedFlight(@RequestBody ConnectedFlightRequestDTO connectedFlightRequestDto) {
        return connectedFlightService.addConnectedFlight(connectedFlightRequestDto);
    }

    @DeleteMapping(value = "/delete-id")
    public ConnectedFlightDTO deleteConnectedFlight(@RequestParam(value = "id") Long connectedFlightId) {
        return connectedFlightService.deleteConnectedFlight(connectedFlightId);
    }

    @PutMapping(value = "/update-connectedFlight")
    public ConnectedFlightDTO updateConnectedFlight(@RequestBody ConnectedFlightRequestDTO connectedFlightRequestDto, @RequestParam(value = "id") Long connectedFlightId) {
        return connectedFlightService.updateConnectedFlight(connectedFlightRequestDto, connectedFlightId);
    }

}
