package com.expressflight.ExpressFlight.microservice;

import com.expressflight.ExpressFlight.domain.*;
import com.expressflight.ExpressFlight.dto.TicketDTO;
import com.expressflight.ExpressFlight.enums.SeatStatus;
import com.expressflight.ExpressFlight.enums.TicketType;
import com.expressflight.ExpressFlight.repository.*;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TicketBooker {

    ITicketRepository ticketRepository;
    IFlightRepository flightRepository;
    ISeatConfigurationRepository seatConfigurationRepository;
    IPassengerRepository passengerRepository;
    ISeatRepository seatRepository;
    ModelMapper modelMapper;
    public TicketBooker(ITicketRepository ticketRepository, IFlightRepository flightRepository,
                        ISeatConfigurationRepository seatConfigurationRepository, IPassengerRepository passengerRepository,
                        ISeatRepository seatRepository,ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
        this.seatConfigurationRepository = seatConfigurationRepository;
        this.passengerRepository = passengerRepository;
        this.seatRepository = seatRepository;
        this.modelMapper = modelMapper;

    }

    public TicketDTO bookTicket(Long flightId, String seatCode, Long passengerId) {

        Ticket ticket = new Ticket();


        if(getSeatAvailability(flightId,seatCode)) {

            Flight flight = flightRepository.findById(flightId).get();
            Seat seat = findSeat(flight,seatCode);
            ticket.setFlight(flightId);
            seat.setStatus(SeatStatus.BOOKED);
            seatRepository.findById(seat.getId()).get().setStatus(SeatStatus.BOOKED);
            ticket.setSeat(seat.getId());
            ticket.setPassenger(passengerId);
            ticket.setTicketType(configureTicketType(passengerId));
            ticket.setPrice(flight.getPrice());
            ticketRepository.save(ticket);
        }
        else {
            System.out.println("Error on Ticket creation");
        }

        TicketDTO ticketDTO = modelMapper.map(ticket,TicketDTO.class);
        return ticketDTO;
    }

    public Boolean getSeatAvailability(Long flightId, String seatCode) {

        Flight flight = flightRepository.findById(flightId).get();
        Boolean isAvailable = false;
        if(isNull(flight) || isNull(seatCode)){
            return null;
        }
        else {
            Seat seatInFlight = findSeat(flight,seatCode);
            if(seatInFlight.getStatus().equals(SeatStatus.FREE)) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    public Seat findSeat(Flight flight, String seatCode) {

        Seat seatInFlight = null;

        if(isNull(flight) || isNull(seatCode) || isNull(flight.getSeatConfig())){
            return null;
        }
        else {
            SeatConfiguration seatConfiguration = seatConfigurationRepository.findById(flight.getSeatConfig()).get();

            int row = Integer.parseInt(seatCode.substring(0,seatCode.length()-1));
            int column = (int) seatCode.charAt(seatCode.length() - 1) -64;

            seatInFlight = seatConfiguration.getSeatMap()[row-1][column-1];
        }
        return seatInFlight;
    }

    public Boolean isNull(Object object) {

        Boolean isNull = false;
        if(object == null) {
            isNull = true;
        }
        else {
            isNull = false;
        }

        return isNull;
    }

    public TicketType configureTicketType(Long passengerId) {

        Passenger passenger = passengerRepository.findById(passengerId).get();

        if(isNull(passenger)) {
            return null;
        }
        else {
            Integer age = passenger.getAge();
            TicketType ticketType = TicketType.UNKNOWN;


            if (age <= 2) {
                ticketType = TicketType.INFANT;
            } else if (age <= 8) {
                ticketType = TicketType.CHILD;
            } else {
                ticketType = TicketType.ADULT;
            }
            return ticketType;
        }
    }

}
