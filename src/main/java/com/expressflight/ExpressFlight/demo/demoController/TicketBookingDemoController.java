package com.expressflight.ExpressFlight.demo.demoController;


import com.expressflight.ExpressFlight.demo.demoService.TicketBookingDemoService;
import com.expressflight.ExpressFlight.dto.TicketDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/demo/bookTickets")
@CrossOrigin(origins = "*")
public class TicketBookingDemoController {


    TicketBookingDemoService ticketBookingDemoService;


    public TicketBookingDemoController(TicketBookingDemoService ticketBookingDemoService) {
        this.ticketBookingDemoService = ticketBookingDemoService;
    }

    @GetMapping
    public List<TicketDTO> bookTickets() { return ticketBookingDemoService.bookTickets(); }


}
