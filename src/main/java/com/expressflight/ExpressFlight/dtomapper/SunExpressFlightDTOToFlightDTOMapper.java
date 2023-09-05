package com.expressflight.ExpressFlight.dtomapper;

import com.expressflight.ExpressFlight.domain.Airport;
import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.integration.provider.SunExpressFlightDTO;
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
        String seatConfigName = "SunExpress7378HC";
        SunExpressFlightDTO sunExpressFlightDto = (SunExpressFlightDTO) mapped;
        FlightRequestDTO flightDto = new FlightRequestDTO();
        Airport depAirport = airportRepository.findByCodeIATA(sunExpressFlightDto.getDepAirport()).get();
        Airport arvAirport = airportRepository.findByCodeIATA(sunExpressFlightDto.getArvAirport()).get();
        flightDto.setDepAirport(depAirport.getId());
        flightDto.setArvAirport(arvAirport.getId());
        flightDto.setDepDateTime(LocalDateTime.of(sunExpressFlightDto.getDepDate(),sunExpressFlightDto.getDepTime()));
        flightDto.setArvDateTime(LocalDateTime.of(sunExpressFlightDto.getArvDate(),sunExpressFlightDto.getArvTime()));
        flightDto.setFlightCode(sunExpressFlightDto.getFlightCode());
        flightDto.setAirline(sunExpressFlightDto.getAirline());
        flightDto.setPrice(sunExpressFlightDto.getPrice());
        if(depAirport.getCountry().equals(arvAirport.getCountry()))
            flightDto.setIsInternational(true);
        else
            flightDto.setIsInternational(false);
        flightDto.setSeatConfigName("SunExpress7378HC");
        SeatConfiguration seatConfiguration = new SeatConfiguration("737-800",seatConfigName,false);
        Long seatConfigId = seatConfigurationRepository.save(seatConfiguration).getId();
        seatConfigurationService.configureSeatConfiguration(seatConfigId);
        flightDto.setSeatConfig(seatConfigId);
        return flightDto;
    }

}
