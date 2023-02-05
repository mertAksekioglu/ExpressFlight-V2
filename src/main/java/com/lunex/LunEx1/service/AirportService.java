package com.lunex.LunEx1.service;

import com.google.gson.Gson;
import com.lunex.LunEx1.domain.Airport;
import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.dto.AirportDTO;
import com.lunex.LunEx1.repository.IAirportRepository;
import com.lunex.LunEx1.serviceInterface.IAirportService;
import com.lunex.LunEx1.util.IWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService implements IAirportService {

    @Autowired
    private IAirportRepository airportRepository;

    @Autowired
    private Gson gson;
    @Autowired
    private IWriter writer;

    @Autowired
    private ModelMapper modelMapper;

    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\airport_data.json";

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
    public AirportDTO getAirportByCode(String airportCode) {
        Optional<Airport> airport = airportRepository.findByCode(airportCode);
        if(!airport.isPresent()) {
            throw new IllegalStateException("Airport with code " + airportCode + " does not exist");
        }
        AirportDTO returningAirportDto = modelMapper.map(airport.get(), AirportDTO.class);
        return returningAirportDto;
    }

    @Override
    public AirportDTO addAirport(AirportDTO airportDto) {
        Airport airport = modelMapper.map(airportDto, Airport.class);
        Optional<Airport> existingAirport = airportRepository.findByCode(airport.getCodeIATA());
        if(existingAirport.isPresent()) {
            throw new IllegalStateException("Airport with code " + airport.getCodeIATA() + " already exists.");
        }
        airportRepository.save(airport);
        writer.write(airportRepository,DATA_PATH);
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
        writer.write(airportRepository,DATA_PATH);
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
        if(airport.getRunwayCount() != null) {
            existingAirport.get().setRunwayCount(airport.getRunwayCount());
        }

        if(airport.getTerminalCount() != null) {
            existingAirport.get().setTerminalCount(airport.getTerminalCount());
        }

        writer.write(airportRepository,DATA_PATH);
        AirportDTO returningAirportDto = modelMapper.map(existingAirport.get(), AirportDTO.class);
        return returningAirportDto;
    }
}
