package com.lunex.LunEx1.mapper;

import com.lunex.LunEx1.dto.FlightDTO;
import com.lunex.LunEx1.dto.SunExpressFlightDTO;
import com.lunex.LunEx1.repository.IAirportRepository;
import com.lunex.LunEx1.service.AirportService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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