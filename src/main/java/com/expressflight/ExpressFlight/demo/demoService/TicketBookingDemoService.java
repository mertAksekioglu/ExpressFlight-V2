package com.expressflight.ExpressFlight.demo.demoService;

import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.domain.Passenger;
import com.expressflight.ExpressFlight.dto.TicketDTO;
import com.expressflight.ExpressFlight.microservice.TicketBooker;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.repository.IPassengerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketBookingDemoService {

    TicketBooker ticketBooker;

    IPassengerRepository passengerRepository;

    IFlightRepository flightRepository;


    public TicketBookingDemoService(TicketBooker ticketBooker, IPassengerRepository passengerRepository, IFlightRepository flightRepository) {
        this.ticketBooker = ticketBooker;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    public List<TicketDTO> bookTickets() {

        List<TicketDTO> tickets = new ArrayList<TicketDTO>();

        Flight flight = flightRepository.findById(1L).get();

        Passenger p1 = passengerRepository.findById(1L).get();
        Passenger p2 = passengerRepository.findById(2L).get();

        tickets.add(ticketBooker.bookTicket(flight.getId(),"1A",p1.getId()));
        tickets.add(ticketBooker.bookTicket(flight.getId(), "1C", p2.getId()));


        return tickets;
    }

}
