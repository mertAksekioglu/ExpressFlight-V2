package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.SeatDTO;
import com.expressflight.ExpressFlight.requestdto.SeatRequestDTO;
import com.expressflight.ExpressFlight.service.SeatService;
import com.expressflight.ExpressFlight.serviceInterface.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/seat")
@CrossOrigin(origins = "*")
public class SeatController {

    private ISeatService seatService;

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
    public SeatDTO addSeat(@RequestBody SeatRequestDTO seatRequestDto) {
        return seatService.addSeat(seatRequestDto);
    }

    @DeleteMapping(value = "/delete-id")
    public SeatDTO deleteSeat(@RequestParam(value = "id") Long seatId) {
        return seatService.deleteSeat(seatId);
    }

    @PutMapping(value = "/update-seat")
    public SeatDTO updateSeat(@RequestBody SeatRequestDTO seatRequestDto, @RequestParam(value = "id") Long seatId) {
        return seatService.updateSeat(seatRequestDto, seatId);
    }

}
