package com.expressflight.ExpressFlight.dto;

import com.expressflight.ExpressFlight.enums.TicketType;
import lombok.Data;

@Data
public class BookTicketRequestDTO {

    private Long flight;
    private Long passenger;
    private String seatCode;
    private TicketType ticketType;
}
