package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.TicketDTO;
import com.expressflight.ExpressFlight.requestdto.TicketRequestDTO;

import java.util.List;

public interface ITicketService {

    public List<TicketDTO> getAllTickets();

    public TicketDTO getTicket(Long ticketId);

    public TicketDTO addTicket(TicketRequestDTO ticketRequestDto);

    public TicketDTO deleteTicket(Long ticketId);

    public TicketDTO updateTicket(TicketRequestDTO ticketRequestDto, Long ticketId);

}



