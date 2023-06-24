package com.expressflight.ExpressFlight.dto;

import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.domain.Passenger;
import com.expressflight.ExpressFlight.domain.Seat;
import com.expressflight.ExpressFlight.enums.TicketType;
import lombok.Data;

@Data
public class TicketDTO {

    private FlightDTO flight;
    private PassengerDTO passenger;
    private SeatDTO seat;
    private TicketType ticketType;

}
