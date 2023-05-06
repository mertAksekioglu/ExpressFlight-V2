package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Ticket;
import com.expressflight.ExpressFlight.dto.TicketDTO;
import com.expressflight.ExpressFlight.microservice.SeatAvailability;
import com.expressflight.ExpressFlight.repository.ISeatRepository;
import com.expressflight.ExpressFlight.repository.ITicketRepository;
import com.expressflight.ExpressFlight.serviceInterface.ITicketService;
import com.expressflight.ExpressFlight.util.IWriter;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {

    private final String DATA_PATH = "D:\\Spring MVC Projects\\ExpressFlight\\src\\main\\resources\\ticket_data.json";
    private final boolean UPDATE_JSON = false;

    @Autowired
    private ITicketRepository ticketRepository;

    @Autowired
    private ISeatRepository seatRepository;

    @Autowired
    private Gson gson;
    @Autowired
    private IWriter writer;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SeatAvailability seatAvailability;



    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> ticketDtos = new ArrayList<>();

        for (Ticket ticket: tickets) {
            TicketDTO ticketDto = modelMapper.map(ticket, TicketDTO.class);
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
        TicketDTO returningTicketDto = modelMapper.map(ticket.get(), TicketDTO.class);
        return returningTicketDto;
    }



    @Override
    public TicketDTO addTicket(TicketDTO ticketDto) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);

        // Todo Code that finds the flight and seat of the ticketDTO then checks if the seat is free

       /* if(seatAvailability.getSeatAvailability(ticket)) {
            String seatCode = seatRepository.findById(ticket.getSeat()).get().getCode();
            throw new IllegalStateException("Ticket with the seat " + seatCode + " is already booked.");
        }*/

        ticketRepository.save(ticket);
        writer.write(ticketRepository,DATA_PATH,UPDATE_JSON);
        TicketDTO returningTicketDto = modelMapper.map(ticket, TicketDTO.class);
        return returningTicketDto;

    }

    @Override
    public TicketDTO deleteTicket(Long ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if(ticket.isEmpty()) {
            throw new IllegalStateException( "Ticket with id " + ticketId + " does not exist");
        }

        ticketRepository.deleteById(ticketId);
        writer.write(ticketRepository,DATA_PATH,UPDATE_JSON);
        TicketDTO returningTicketDto = modelMapper.map(ticket.get(), TicketDTO.class);
        return returningTicketDto;
    }


    @Transactional
    public TicketDTO updateTicket(TicketDTO ticketDto, Long ticketId) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
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

        writer.write(ticketRepository,DATA_PATH,UPDATE_JSON);
        TicketDTO returningTicketDto = modelMapper.map(existingTicket.get(), TicketDTO.class);
        return returningTicketDto;
    }
}
