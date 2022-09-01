package com.lunex.LunEx1.service;

import com.lunex.LunEx1.domain.Airport;
import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.repository.IAirportRepository;
import com.lunex.LunEx1.serviceInterface.IAirportService;
import com.lunex.LunEx1.util.IWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService implements IAirportService {

    @Autowired
    private IAirportRepository airportRepository;

    @Autowired
    private IWriter writer;

    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\airport_data.json";

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport getAirport(Long airportId) {
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new IllegalStateException(
                        "Airport with id " + airportId + " does not exist"
                ));;
        return airport;
    }

    @Override
    public void addAirport(Airport airport) {
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
    public void updateAirport(Airport airport) {
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
