package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Airport;
import com.expressflight.ExpressFlight.domain.ConnectedFlight;
import com.expressflight.ExpressFlight.domain.Coordinate;
import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.dto.AirportDTO;
import com.expressflight.ExpressFlight.dto.ConnectedFlightDTO;
import com.expressflight.ExpressFlight.dto.CoordinateDTO;
import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.repository.ICoordinateRepository;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.serviceInterface.IFlightService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class ConnectedFlightDTOMapper {

    private ModelMapper modelMapper;

    private IFlightService flightService;

    public ConnectedFlightDTOMapper(ModelMapper modelMapper, IFlightService flightService) {
        this.modelMapper = modelMapper;
        this.flightService = flightService;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<ConnectedFlight, ConnectedFlightDTO> Converter = new Converter<ConnectedFlight, ConnectedFlightDTO>()
        {
            public ConnectedFlightDTO convert(MappingContext<ConnectedFlight, ConnectedFlightDTO> context)
            {
                ConnectedFlight s = context.getSource();
                ConnectedFlightDTO d = context.getDestination();
                FlightDTO[] flightLegs = new FlightDTO[s.getFlightLegs().length];
                for(int i = 0; i<s.getFlightLegs().length; i++) {
                    flightLegs[i] = flightService.getFlight(s.getFlightLegs()[i]);
                }
                d.setFlightLegs(flightLegs);
                d.setStopCount(s.getStopCount());
                d.setPrice(s.getPrice());
                d.setLayoverMinutes(s.getLayoverMinutes());
                return d;
            }
        };

        modelMapper.addConverter(Converter);
    }

}
