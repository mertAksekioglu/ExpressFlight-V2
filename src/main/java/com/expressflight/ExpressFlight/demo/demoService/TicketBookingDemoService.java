package com.expressflight.ExpressFlight.demo.demoService;

import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.dto.TicketDTO;
import com.expressflight.ExpressFlight.microservice.TicketBooker;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.repository.IPassengerRepository;
import com.expressflight.ExpressFlight.requestdto.BookTicketRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingDemoService {

    TicketBooker ticketBooker;

    IPassengerRepository passengerRepository;

    IFlightRepository flightRepository;

    public TicketBookingDemoService(TicketBooker ticketBooker, IPassengerRepository passengerRepository,
                                    IFlightRepository flightRepository) {
        this.ticketBooker = ticketBooker;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    public TicketDTO bookTicket(BookTicketRequestDTO bookTicketRequestDTO) {
        Flight flight = flightRepository.findById(bookTicketRequestDTO.getFlight()).get();
        TicketDTO ticketDTO = ticketBooker.bookTicket(flight.getId(), bookTicketRequestDTO.getSeatCode(), bookTicketRequestDTO.getPassenger());
        return ticketDTO;
    }

}