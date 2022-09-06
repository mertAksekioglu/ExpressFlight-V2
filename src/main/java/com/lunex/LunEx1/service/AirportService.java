package com.lunex.LunEx1.service;

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
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new IllegalStateException(
                        "Airport with id " + airportId + " does not exist"
                ));;
        AirportDTO airportDto = modelMapper.map(airport, AirportDTO.class);
        return airportDto;
    }

    @Override
    public void addAirport(AirportDTO airportDto) {
        Airport airport = modelMapper.map(airportDto, Airport.class);
        Optional<Airport> existingAirport = airportRepository.findByCode(airport.getCode());
        if(existingAirport.isPresent()) {
            throw new IllegalStateException("Airport with code " + airport.getCode() + " already exists.");
        }
        airportRepository.save(airport);
        writer.write(airportRepository,DATA_PATH);

    }

    @Override
    public void deleteAirport(Long airportId) {
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new IllegalStateException(
                        "Airport with id " + airportId + " does not exist"
                ));;


                airportRepository.deleteById(airportId);
        writer.write(airportRepository,DATA_PATH);
    }

    @Override
    @Transactional
    public void updateAirport(AirportDTO airportDto) {
        Airport airport = modelMapper.map(airportDto, Airport.class);
        Airport existingAirport = airportRepository.findById(airport.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Airport with id " + airport.getId() + " does not exist"
                ));;
        if(airport.getCode() != null) {
          existingAirport.setCode(airport.getCode());
        }

        if(airport.getRunwayCount() != null) {
            existingAirport.setRunwayCount(airport.getRunwayCount());
        }

        if(airport.getTerminalCount() != null) {
            existingAirport.setTerminalCount(airport.getTerminalCount());
        }

        writer.write(airportRepository,DATA_PATH);

    }
}
