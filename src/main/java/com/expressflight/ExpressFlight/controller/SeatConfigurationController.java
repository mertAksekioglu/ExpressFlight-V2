package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.SeatConfigurationDTO;
import com.expressflight.ExpressFlight.requestdto.SeatConfigurationRequestDTO;
import com.expressflight.ExpressFlight.service.SeatConfigurationService;
import com.expressflight.ExpressFlight.serviceInterface.ISeatConfigurationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/seatConfiguration")
@CrossOrigin(origins = "*")
public class SeatConfigurationController {

    private ISeatConfigurationService seatConfigurationService;

    public SeatConfigurationController(SeatConfigurationService seatConfigurationService) {
        this.seatConfigurationService = seatConfigurationService;
    }

    @GetMapping
    public List<SeatConfigurationDTO> getAllSeatConfigurations() {
        return seatConfigurationService.getAllSeatConfigurations();
    }

    @GetMapping(value = "/get-id")
    public SeatConfigurationDTO getSeatConfiguration(@RequestParam(value = "id") Long seatConfigurationId) {
        return seatConfigurationService.getSeatConfiguration(seatConfigurationId);
    }

    @GetMapping(value = "/get-configName")
    public SeatConfigurationDTO getSeatConfiguration(@RequestParam(value = "configName") String seatConfigurationConfigName) {
        return seatConfigurationService.getSeatConfigurationByConfigName(seatConfigurationConfigName);
    }

    @PostMapping(value = "/add-seatConfiguration")
    public SeatConfigurationDTO addSeatConfiguration(@RequestBody SeatConfigurationRequestDTO seatConfigurationRequestDto) {
        return seatConfigurationService.addSeatConfiguration(seatConfigurationRequestDto);
    }

    @DeleteMapping(value = "/delete-id")
    public SeatConfigurationDTO deleteSeatConfiguration(@RequestParam(value = "id") Long seatConfigurationId) {
        return seatConfigurationService.deleteSeatConfiguration(seatConfigurationId);
    }

    @PutMapping(value = "/update-seatConfiguration")
    public SeatConfigurationDTO updateSeatConfiguration(@RequestBody SeatConfigurationRequestDTO seatConfigurationRequestDto, @RequestParam(value = "id") Long seatConfigurationId) {
        return seatConfigurationService.updateSeatConfiguration(seatConfigurationRequestDto, seatConfigurationId);
    }
    
}
