package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.provider.SunExpressFlightDTO;
import com.expressflight.ExpressFlight.repository.IAirportRepository;
import com.expressflight.ExpressFlight.repository.ISeatConfigurationRepository;
import com.expressflight.ExpressFlight.requestdto.FlightRequestDTO;
import com.expressflight.ExpressFlight.service.SeatConfigurationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SunExpressFlightDTOToFlightDTOMapper implements IDTOMapper {

    ModelMapper modelMapper;

    IAirportRepository airportRepository;

    ISeatConfigurationRepository seatConfigurationRepository;

    SeatConfigurationService seatConfigurationService;

    public SunExpressFlightDTOToFlightDTOMapper(ModelMapper modelMapper, IAirportRepository airportRepository,
                                                ISeatConfigurationRepository seatConfigurationRepository,
                                                SeatConfigurationService seatConfigurationService) {
        this.modelMapper = modelMapper;
        this.airportRepository = airportRepository;
        this.seatConfigurationRepository = seatConfigurationRepository;
        this.seatConfigurationService = seatConfigurationService;
    }

    @Override
    public Object map(Object mapped, Object mapper) {
        SunExpressFlightDTO sunExpressFlightDto = (SunExpressFlightDTO) mapped;
        FlightRequestDTO flightDto = new FlightRequestDTO();
        flightDto.setDepAirport(airportRepository.findByCodeIATA(sunExpressFlightDto.getDepAirport()).get().getId());
        flightDto.setArvAirport(airportRepository.findByCodeIATA(sunExpressFlightDto.getArvAirport()).get().getId());
        flightDto.setDepDateTime(LocalDateTime.of(sunExpressFlightDto.getDepDate(),sunExpressFlightDto.getDepTime()));
        flightDto.setArvDateTime(LocalDateTime.of(sunExpressFlightDto.getArvDate(),sunExpressFlightDto.getArvTime()));
        flightDto.setFlightCode(sunExpressFlightDto.getFlightCode());
        flightDto.setAirline(sunExpressFlightDto.getAirline());
        flightDto.setPrice(sunExpressFlightDto.getPrice());
        SeatConfiguration seatConfiguration = new SeatConfiguration("737-800","SunExpress7378HC",false);
        Long seatConfigId = seatConfigurationRepository.save(seatConfiguration).getId();
        seatConfigurationService.configureSeatConfiguration(seatConfigId);
        flightDto.setSeatConfig(seatConfigId);
        return flightDto;
    }

}
