package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Airport;
import com.expressflight.ExpressFlight.dto.AirportDTO;
import com.expressflight.ExpressFlight.repository.IAirportRepository;
import com.expressflight.ExpressFlight.requestdto.AirportRequestDTO;
import com.expressflight.ExpressFlight.serviceInterface.IAirportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService implements IAirportService {

    private IAirportRepository airportRepository;

    private ModelMapper modelMapper;

    public AirportService(IAirportRepository airportRepository, ModelMapper modelMapper) {
        this.airportRepository = airportRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AirportDTO> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airports.stream()
                .map(airport -> convertToDTO(airport))
                .collect(Collectors.toList());
    }

    @Override
    public AirportDTO getAirport(Long airportId) {
        Optional<Airport> airport = airportRepository.findById(airportId);
        if(!airport.isPresent()) {
            throw new IllegalStateException("Airport with id " + airportId + " does not exist");
        }
        AirportDTO returningAirportDto = convertToDTO(airport.get());
        return returningAirportDto;
    }

    @Override
    public AirportDTO getAirportByCodeIATA(String airportCodeIATA) {
        Optional<Airport> airport = airportRepository.findByCodeIATA(airportCodeIATA);
        if(!airport.isPresent()) {
            throw new IllegalStateException("Airport with IATA code " + airportCodeIATA + " does not exist");
        }
        return convertToDTO(airport.get());
    }

    @Override
    public AirportDTO addAirport(AirportRequestDTO airportRequestDto) {
        Airport airport = convertToEntity(airportRequestDto);
        Optional<Airport> existingAirport = airportRepository.findByCodeIATA(airport.getCodeIATA());
        if(existingAirport.isPresent()) {
            throw new IllegalStateException("Airport with code " + airport.getCodeIATA() + " already exists.");
        }
        airportRepository.save(airport);
        return convertToDTO(airport);

    }
    @Override
    public AirportDTO deleteAirport(Long airportId) {
        Optional<Airport> airport = airportRepository.findById(airportId);
        if(!airport.isPresent()) {
            throw new IllegalStateException( "Airport with id " + airportId + " does not exist");
        }
        airportRepository.deleteById(airportId);
        return convertToDTO(airport.get());
    }

    @Override
    @Transactional
    public AirportDTO updateAirport(AirportRequestDTO airportRequestDto, Long airportId) {
        Airport airport = convertToEntity(airportRequestDto);
        Optional<Airport> existingAirport = airportRepository.findById(airportId);
        if(!existingAirport.isPresent()) {
            throw new IllegalStateException( "Airport with id " + airport.getId() + " does not exist");
        }
        if(airport.getName() != null) {
            existingAirport.get().setName(airport.getName());
        }
        if(airport.getCodeIATA() != null) {
          existingAirport.get().setCodeIATA(airport.getCodeIATA());
        }
        if(airport.getCodeICAO() != null) {
            existingAirport.get().setCodeICAO(airport.getCodeICAO());
        }
        if(airport.getLocation() != null) {
            existingAirport.get().setLocation(airport.getLocation());
        }
        if(airport.getCity() != null) {
            existingAirport.get().setCity(airport.getCity());
        }
        if(airport.getCountry() != null) {
            existingAirport.get().setCountry(airport.getCountry());
        }
        if(airport.getRunwayCount() != null) {
            existingAirport.get().setRunwayCount(airport.getRunwayCount());
        }
        if(airport.getTerminalCount() != null) {
            existingAirport.get().setTerminalCount(airport.getTerminalCount());
        }
        return convertToDTO(existingAirport.get());
    }

    private AirportDTO convertToDTO(Airport airport) {
        AirportDTO AirportDto = modelMapper.map(airport, AirportDTO.class);
        return AirportDto;
    }

    private Airport convertToEntity(AirportRequestDTO airportRequestDto) {
        Airport airport = modelMapper.map(airportRequestDto, Airport.class);
        return airport;
    }

}
