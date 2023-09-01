package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.PassengerDTO;
import com.expressflight.ExpressFlight.requestdto.PassengerRequestDTO;
import com.expressflight.ExpressFlight.service.PassengerService;
import com.expressflight.ExpressFlight.serviceInterface.IPassengerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/passenger")
@CrossOrigin(origins = "*")
public class PassengerController {

    private IPassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping(value = "/get-id")
    public PassengerDTO getPassenger(@RequestParam(value = "id") Long passengerId) {
        return passengerService.getPassenger(passengerId);
    }

    @PostMapping(value = "/add-passenger")
    public PassengerDTO addPassenger(@RequestBody PassengerRequestDTO passengerRequestDto) {
        return passengerService.addPassenger(passengerRequestDto);
    }

    @DeleteMapping(value = "/delete-id")
    public PassengerDTO deletePassenger(@RequestParam(value = "id") Long passengerId) {
        return passengerService.deletePassenger(passengerId);
    }

    @PutMapping(value = "/update-passenger")
    public PassengerDTO updatePassenger(@RequestBody PassengerRequestDTO passengerRequestDto, @RequestParam(value = "id") Long passengerId) {
        return passengerService.updatePassenger(passengerRequestDto, passengerId);
    }

}
