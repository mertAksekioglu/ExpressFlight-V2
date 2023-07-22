package com.expressflight.ExpressFlight.requestdto;

import com.expressflight.ExpressFlight.enums.TicketType;
import lombok.Data;

@Data
public class TicketRequestDTO {


    private Long flight;
    private Long passenger;
    private Long seat;
    private TicketType ticketType;
    private Double price;

}
