package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Airport;
import com.expressflight.ExpressFlight.dto.AirportDTO;
import com.expressflight.ExpressFlight.repository.IAirportRepository;
import com.expressflight.ExpressFlight.serviceInterface.IAirportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<AirportDTO> airportDtos = new ArrayList<>();
        for (Airport airport: airports) {
            AirportDTO airportDto = modelMapper.map(airport, AirportDTO.class);
            airportDtos.add(airportDto);
        }
        return airportDtos;
    }

    @Override
    public AirportDTO getAirport(Long airportId) {
        Optional<Airport> airport = airportRepository.findById(airportId);
        if(!airport.isPresent()) {
            throw new IllegalStateException("Airport with id " + airportId + " does not exist");
        }
        AirportDTO returningAirportDto = modelMapper.map(airport.get(), AirportDTO.class);
        return returningAirportDto;
    }

    @Override
    public AirportDTO getAirportByCodeIATA(String airportCodeIATA) {
        Optional<Airport> airport = airportRepository.findByCodeIATA(airportCodeIATA);
        if(!airport.isPresent()) {
            throw new IllegalStateException("Airport with IATA code " + airportCodeIATA + " does not exist");
        }
        AirportDTO returningAirportDto = modelMapper.map(airport.get(), AirportDTO.class);
        return returningAirportDto;
    }

    @Override
    public AirportDTO addAirport(AirportDTO airportDto) {
        Airport airport = modelMapper.map(airportDto, Airport.class);
        Optional<Airport> existingAirport = airportRepository.findByCodeIATA(airport.getCodeIATA());
        if(existingAirport.isPresent()) {
            throw new IllegalStateException("Airport with code " + airport.getCodeIATA() + " already exists.");
        }
        airportRepository.save(airport);
        AirportDTO returningAirportDto = modelMapper.map(airport, AirportDTO.class);
        return returningAirportDto;

    }
    @Override
    public AirportDTO deleteAirport(Long airportId) {
        Optional<Airport> airport = airportRepository.findById(airportId);
        if(!airport.isPresent()) {
            throw new IllegalStateException( "Airport with id " + airportId + " does not exist");
        }
        airportRepository.deleteById(airportId);
        AirportDTO returningAirportDto = modelMapper.map(airport.get(), AirportDTO.class);
        return returningAirportDto;
    }

    @Override
    @Transactional
    public AirportDTO updateAirport(AirportDTO airportDto, Long airportId) {
        Airport airport = modelMapper.map(airportDto, Airport.class);
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
        if(airport.getRunwayCount() != null) {
            existingAirport.get().setRunwayCount(airport.getRunwayCount());
        }
        if(airport.getTerminalCount() != null) {
            existingAirport.get().setTerminalCount(airport.getTerminalCount());
        }
        AirportDTO returningAirportDto = modelMapper.map(existingAirport.get(), AirportDTO.class);
        return returningAirportDto;
    }

}
