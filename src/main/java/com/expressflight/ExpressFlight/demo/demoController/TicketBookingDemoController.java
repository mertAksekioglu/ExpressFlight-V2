package com.expressflight.ExpressFlight.demo.demoController;


import com.expressflight.ExpressFlight.demo.demoService.TicketBookingDemoService;
import com.expressflight.ExpressFlight.dto.BookTicketRequestDTO;
import com.expressflight.ExpressFlight.dto.TicketDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/demo/bookTicket")
@CrossOrigin(origins = "*")
public class TicketBookingDemoController {

    TicketBookingDemoService ticketBookingDemoService;

    public TicketBookingDemoController(TicketBookingDemoService ticketBookingDemoService) {
        this.ticketBookingDemoService = ticketBookingDemoService;
    }

    @PostMapping
    public TicketDTO bookTicket(@RequestBody BookTicketRequestDTO bookTicketRequestDTO) { return ticketBookingDemoService.bookTicket(bookTicketRequestDTO); }

}
