package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.SeatDTO;
import com.expressflight.ExpressFlight.service.SeatService;
import com.expressflight.ExpressFlight.serviceInterface.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/seat")
@CrossOrigin(origins = "*")
public class SeatController {

    @Autowired
    private ISeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<SeatDTO> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping(value = "/get-id")
    public SeatDTO getSeat(@RequestParam(value = "id") Long seatId) {
        return seatService.getSeat(seatId);
    }

    @GetMapping(value = "/get-code")
    public SeatDTO getSeat(@RequestParam(value = "code") String seatCode) {
        return seatService.getSeatByCode(seatCode);
    }

    @PostMapping(value = "/add-seat")
    public SeatDTO addSeat(@RequestBody SeatDTO seatDto) {
        return seatService.addSeat(seatDto);
    }

    @DeleteMapping(value = "/delete-id")
    public SeatDTO deleteSeat(@RequestParam(value = "id") Long seatId) {
        return seatService.deleteSeat(seatId);
    }

    @PutMapping(value = "/update-seat")
    public SeatDTO updateSeat(@RequestBody SeatDTO seatDto, @RequestParam(value = "id") Long seatId) {
        return seatService.updateSeat(seatDto, seatId);
    }



}
