package com.expressflight.ExpressFlight.controller;


import com.expressflight.ExpressFlight.dto.TicketDTO;
import com.expressflight.ExpressFlight.service.TicketService;
import com.expressflight.ExpressFlight.serviceInterface.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket")
@CrossOrigin(origins = "*")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping(value = "/get-id")
    public TicketDTO getTicket(@RequestParam(value = "id") Long ticketId) {
        return ticketService.getTicket(ticketId);
    }

    @PostMapping(value = "/add-ticket")
    public TicketDTO addTicket(@RequestBody TicketDTO ticketDto) {
        return ticketService.addTicket(ticketDto);
    }

    @DeleteMapping(value = "/delete-id")
    public TicketDTO deleteTicket(@RequestParam(value = "id") Long ticketId) {
        return ticketService.deleteTicket(ticketId);
    }

    @PutMapping(value = "/update-ticket")
    public TicketDTO updateTicket(@RequestBody TicketDTO ticketDto, @RequestParam(value = "id") Long ticketId) {
        return ticketService.updateTicket(ticketDto, ticketId);
    }



}
