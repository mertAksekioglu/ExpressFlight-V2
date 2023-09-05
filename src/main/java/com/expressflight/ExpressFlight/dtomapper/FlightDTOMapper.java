package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.dto.AirportDTO;
import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.dto.SeatConfigurationDTO;
import com.expressflight.ExpressFlight.serviceInterface.IAirportService;
import com.expressflight.ExpressFlight.serviceInterface.ISeatConfigurationService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class FlightDTOMapper {

    private ModelMapper modelMapper;

    private IAirportService airportService;

    private ISeatConfigurationService seatConfigurationService;

    public FlightDTOMapper(ModelMapper modelMapper, IAirportService airportService, ISeatConfigurationService seatConfigurationService) {
        this.modelMapper = modelMapper;
        this.airportService = airportService;
        this.seatConfigurationService = seatConfigurationService;
        addCustomMapping();
    }

    private void addCustomMapping() {
        Converter<Flight, FlightDTO> Converter = new Converter<Flight, FlightDTO>()
        {
            public FlightDTO convert(MappingContext<Flight, FlightDTO> context)
            {
                Flight s = context.getSource();
                FlightDTO d = context.getDestination();

                d.setAirline(s.getAirline());
                d.setFlightCode(s.getFlightCode());
                d.setPrice(s.getPrice());
                AirportDTO depAirport = airportService.getAirport(s.getDepAirport());
                d.setDepAirport(depAirport);
                AirportDTO arvAirport = airportService.getAirport(s.getArvAirport());
                d.setArvAirport(arvAirport);
                d.setDepDateTime(s.getDepDateTime());
                d.setArvDateTime(s.getArvDateTime());
                d.setIsInternational(s.getIsInternational());
                d.setSeatConfigName(s.getSeatConfigName());
                SeatConfigurationDTO seatConfiguration = seatConfigurationService.getSeatConfiguration(s.getSeatConfig());
                seatConfiguration = null;
                d.setSeatConfig(seatConfiguration);
                return d;
            }
        };
        modelMapper.addConverter(Converter);
    }

}
