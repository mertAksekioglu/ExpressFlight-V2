package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Ticket;
import com.expressflight.ExpressFlight.dto.*;
import com.expressflight.ExpressFlight.enums.TicketType;
import com.expressflight.ExpressFlight.serviceInterface.ICoordinateService;
import com.expressflight.ExpressFlight.serviceInterface.IFlightService;
import com.expressflight.ExpressFlight.serviceInterface.IPassengerService;
import com.expressflight.ExpressFlight.serviceInterface.ISeatService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class TicketDTOMapper {

    private ModelMapper modelMapper;

    private IFlightService flightService;

    private IPassengerService passengerService;

    private ISeatService seatService;

    public TicketDTOMapper(ModelMapper modelMapper, IFlightService flightService, 
                           IPassengerService passengerService, ISeatService seatService) {
        this.modelMapper = modelMapper;
        this.flightService = flightService;
        this.passengerService = passengerService;
        this.seatService = seatService;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<Ticket, TicketDTO> Converter = new Converter<Ticket, TicketDTO>()
        {
            public TicketDTO convert(MappingContext<Ticket, TicketDTO> context)
            {
                Ticket s = context.getSource();
                TicketDTO d = context.getDestination();

                FlightDTO flight = flightService.getFlight(s.getFlight());
                flight.setSeatConfig(null);
                d.setFlight(flight);
                PassengerDTO passenger = passengerService.getPassenger(s.getPassenger());
                d.setPassenger(passenger);
                SeatDTO seat = seatService.getSeat(s.getSeat());
                d.setSeat(seat);
                d.setTicketType(s.getTicketType());
                d.setPrice(s.getPrice());
                return d;
                
                
                
            }
        };
        modelMapper.addConverter(Converter);
    }

}
