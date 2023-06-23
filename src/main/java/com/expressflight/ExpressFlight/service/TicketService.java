package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Ticket;
import com.expressflight.ExpressFlight.dto.TicketDTO;
import com.expressflight.ExpressFlight.microservice.TicketBooker;
import com.expressflight.ExpressFlight.repository.ISeatRepository;
import com.expressflight.ExpressFlight.repository.ITicketRepository;
import com.expressflight.ExpressFlight.serviceInterface.ITicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {

    private ITicketRepository ticketRepository;

    private ISeatRepository seatRepository;

    private ModelMapper modelMapper;

    private TicketBooker ticketBooker;

    public TicketService(ITicketRepository ticketRepository, ISeatRepository seatRepository,
                         ModelMapper modelMapper, TicketBooker ticketBooker) {
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
        this.modelMapper = modelMapper;
        this.ticketBooker = ticketBooker;
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> ticketDtos = new ArrayList<>();
        for (Ticket ticket: tickets) {
            TicketDTO ticketDto = convertToDTO(ticket);
            ticketDtos.add(ticketDto);
        }
        return ticketDtos;
    }

    @Override
    public TicketDTO getTicket(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if(!ticket.isPresent()) {
            throw new IllegalStateException("Ticket with id " + ticketId + " does not exist");
        }
        return convertToDTO(ticket.get());
    }

    @Override
    public TicketDTO addTicket(TicketDTO ticketDto) {
        Ticket ticket = convertToEntity(ticketDto);
        ticketBooker.getSeatAvailability(ticketDto.getFlight(),seatRepository.findById(ticketDto.getSeat()).get().getCode());
        ticketRepository.save(ticket);
        return convertToDTO(ticket);

    }

    @Override
    public TicketDTO deleteTicket(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if(ticket.isEmpty()) {
            throw new IllegalStateException( "Ticket with id " + ticketId + " does not exist");
        }
        ticketRepository.deleteById(ticketId);
        return convertToDTO(ticket.get());
    }

    @Transactional
    public TicketDTO updateTicket(TicketDTO ticketDto, Long ticketId) {
        Ticket ticket = convertToEntity(ticketDto);
        Optional<Ticket> existingTicket = ticketRepository.findById(ticketId);
        if(!existingTicket.isPresent()) {
            throw new IllegalStateException( "Ticket with id " + ticket.getId() + " does not exist");
        }
        if(ticket.getFlight() != null) {
            existingTicket.get().setFlight(ticket.getFlight());
        }
        if(ticket.getPassenger() != null) {
            existingTicket.get().setPassenger(ticket.getPassenger());
        }
        if(ticket.getSeat() != null) {
            existingTicket.get().setSeat(ticket.getSeat());
        }
        if(ticket.getTicketType() != null) {
            existingTicket.get().setTicketType(ticket.getTicketType());
        }
        return convertToDTO(existingTicket.get());
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO TicketDto = modelMapper.map(ticket, TicketDTO.class);
        return TicketDto;
    }

    private Ticket convertToEntity(TicketDTO ticketDto) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        return ticket;
    }
}
