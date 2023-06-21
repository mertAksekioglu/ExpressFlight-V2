package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.TicketDTO;

import java.util.List;

public interface ITicketService {

    public List<TicketDTO> getAllTickets();

    public TicketDTO getTicket(Long ticketId);

    public TicketDTO addTicket(TicketDTO ticketDto);

    public TicketDTO deleteTicket(Long ticketId);

    public TicketDTO updateTicket(TicketDTO ticketDto, Long ticketId);

}



