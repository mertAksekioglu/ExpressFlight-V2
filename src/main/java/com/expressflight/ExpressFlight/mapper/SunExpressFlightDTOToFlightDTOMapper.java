package com.expressflight.ExpressFlight.mapper;

import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.dto.SunExpressFlightDTO;
import com.expressflight.ExpressFlight.repository.IAirportRepository;
import com.expressflight.ExpressFlight.dto.FlightDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SunExpressFlightDTOToFlightDTOMapper implements IMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IAirportRepository airportRepository;

    @Override
    public Object map(Object mapped, Object mapper) {
        SunExpressFlightDTO sunExpressFlightDto = (SunExpressFlightDTO) mapped;
        FlightDTO flightDto = new FlightDTO();

        flightDto.setDepAirport(airportRepository.findByCodeIATA(sunExpressFlightDto.getDepAirport()).get().getId());
        flightDto.setArvAirport(airportRepository.findByCodeIATA(sunExpressFlightDto.getArvAirport()).get().getId());
        flightDto.setDepDateTime(LocalDateTime.of(sunExpressFlightDto.getDepDate(),sunExpressFlightDto.getDepTime()));
        flightDto.setArvDateTime(LocalDateTime.of(sunExpressFlightDto.getArvDate(),sunExpressFlightDto.getArvTime()));
        flightDto.setFlightCode(sunExpressFlightDto.getFlightCode());
        flightDto.setAirline(sunExpressFlightDto.getAirline());
        flightDto.setPrice(sunExpressFlightDto.getPrice());
        flightDto.setSeatConfig(new SeatConfiguration("737-800","SunExpress7378HC"));
        return flightDto;
        // then it maps via property mapper

    }
}

/*

FlightDTO
    private Long depAirport;
    private Long arvAirport;
    private LocalDateTime depDateTime;
    private LocalDateTime arvDateTime;
    private String flightCode;
    private String airline;
    private Double price;


    SUNEXFLIGHTDTO


    private String depAirport;
    private String arvAirport;
    private LocalDate depDate;
    private LocalTime depTime;
    private LocalDate arvDate;
    private LocalTime arvTime;
    private String flightCode;
    private String airline;
    private Double price;

 */