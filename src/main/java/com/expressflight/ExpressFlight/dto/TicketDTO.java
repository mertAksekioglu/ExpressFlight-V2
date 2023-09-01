package com.expressflight.ExpressFlight.dto;

import com.expressflight.ExpressFlight.enums.TicketType;
import lombok.Data;

@Data
public class TicketDTO {

    private FlightDTO flight;
    private PassengerDTO passenger;
    private SeatDTO seat;
    private TicketType ticketType;
    private Double price;

}
