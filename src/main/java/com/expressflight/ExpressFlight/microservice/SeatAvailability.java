package com.expressflight.ExpressFlight.microservice;

import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.domain.Seat;
import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.domain.Ticket;
import com.expressflight.ExpressFlight.enums.SeatStatus;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.repository.ISeatConfigurationRepository;
import com.expressflight.ExpressFlight.repository.ISeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatAvailability {


    @Autowired
    IFlightRepository flightRepository;

    @Autowired
    ISeatRepository seatRepository;

    @Autowired
    ISeatConfigurationRepository seatConfigurationRepository;

    public Boolean getSeatAvailability(Ticket ticket) {

        // This function will look at a ticket and then decide
        // if the flight in the ticket has the seat in the ticket available

        Optional<Flight> flight = flightRepository.findById(ticket.getFlight());
        Optional<Seat> seat = seatRepository.findById(ticket.getSeat());

        if(seat.isPresent() && flight.isPresent()) {

            String seatCode = seat.get().getCode();
            int seatRow = Integer.parseInt(seatCode.substring(1));
            int seatCol = seatCode.charAt(0) - 65;

            Optional<SeatConfiguration> seatConfig = Optional.ofNullable(flight.get().getSeatConfig());

            if(seatConfig.isPresent()){
                return seatConfig.get().getSeatConfiguration()[seatRow][seatCol].getStatus() == SeatStatus.FREE;
            }
            else { throw new RuntimeException("The given seat configuration does not exist"); }
        }
        else { throw new RuntimeException("The given ticket parameter does not have a flight or a seat"); }

    }






}
