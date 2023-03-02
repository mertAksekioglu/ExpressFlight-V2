package com.lunex.LunEx1.service;

import com.google.gson.Gson;
import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.dto.AirportDTO;
import com.lunex.LunEx1.dto.FlightDTO;
import com.lunex.LunEx1.dto.FlightSearchRequestDTO;
import com.lunex.LunEx1.dto.FlightSegmentDTO;
import com.lunex.LunEx1.integration.SunExpressIntegration;
import com.lunex.LunEx1.repository.IFlightRepository;
import com.lunex.LunEx1.serviceInterface.IFlightService;
import com.lunex.LunEx1.util.IWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements IFlightService {


    @Autowired
    private IFlightRepository flightRepository;

    private final boolean UPDATE_JSON_DATA = false;

    @Autowired
    private Gson gson;
    @Autowired
    private IWriter writer;

    @Autowired
    private ModelMapper modelMapper;




    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\flight_data.json";

    @Override
    public List<FlightDTO> getAllFlights() {

        List<Flight> flights = flightRepository.findAll();
        List<FlightDTO> flightDtos = new ArrayList<>();
        for (Flight existingFlight : flights)
        {
            FlightDTO flightDto = modelMapper.map(existingFlight,FlightDTO.class);
            flightDtos.add(flightDto);
        }






        return flightDtos;
    }
    @Override
    public FlightDTO getFlight(Long flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if(!flight.isPresent()) {
            throw new IllegalStateException("Flight with id " + flightId + " does not exist");
        }
        FlightDTO returningFlightDto = modelMapper.map(flight.get(), FlightDTO.class);






        return returningFlightDto;
    }

/*
    @Override
    public FlightDTO getFlightByCode(String flightCode) {
        Optional<Flight> flight = flightRepository.findByFlightCode(flightCode);
        if(!flight.isPresent()) {
            throw new IllegalStateException("Flight with code " + flightCode + " does not exist");
        }
        FlightDTO returningFlightDto = modelMapper.map(flight.get(), FlightDTO.class);
        return returningFlightDto;
    }
*/
    @Override
    public List<FlightDTO> searchFlight(FlightSearchRequestDTO flightSearchRequestDto) {


        List<Flight> allFlights = flightRepository.findAll();
        List<FlightDTO> resultFlightDtos = new ArrayList<>();

        for (Flight flight: allFlights) {
            LocalDateTime firstFlightDateTime = flight.getDepDateTime();
            Long firstDepAirport = flight.getDepAirport();
            Long lastDesAirport = flight.getArvAirport();

            if(firstDepAirport.equals(flightSearchRequestDto.getDepAirport()) &&
               lastDesAirport.equals(flightSearchRequestDto.getDesAirport()) &&
               firstFlightDateTime.equals(flightSearchRequestDto.getDepDateTime())){
                resultFlightDtos.add(modelMapper.map(flight,FlightDTO.class));
            }

        }

        return resultFlightDtos;
    }



    @Override
    public FlightDTO addFlight(FlightDTO flightDto) {
        Flight flight = modelMapper.map(flightDto,Flight.class);
        flightRepository.save(flight);
        if(UPDATE_JSON_DATA){
            writer.write(flightRepository, DATA_PATH);
        }

        FlightDTO returningFlightDto = modelMapper.map(flight, FlightDTO.class);
        return returningFlightDto;

    }

    @Override
    public FlightDTO deleteFlight(Long flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if(!flight.isPresent()) {
            throw new IllegalStateException("Flight with the id " + flightId + " does not exist");
        }
        flightRepository.deleteById(flightId);
        writer.write(flightRepository, DATA_PATH);


        FlightDTO returningFlightDto = modelMapper.map(flight.get(), FlightDTO.class);
        return returningFlightDto;
    }

    @Override
    @Transactional
    public FlightDTO updateFlight(FlightDTO flightDto, Long flightId) {
        Flight flight = modelMapper.map(flightDto,Flight.class);
        Optional<Flight> existingFlight = flightRepository.findById(flightId);
        if(!existingFlight.isPresent()) {
            throw new IllegalStateException("Flight with the id " + existingFlight.get().getId() + " does not exist");
        }

        if(flight.getDepAirport() != null){
            existingFlight.get().setDepAirport(flight.getDepAirport());
        }
        if(flight.getArvAirport() != null){
            existingFlight.get().setArvAirport(flight.getArvAirport());
        }
        if(flight.getDepDateTime() != null){
            existingFlight.get().setDepDateTime(flight.getDepDateTime());
        }
        if(flight.getArvDateTime() != null){
            existingFlight.get().setArvDateTime(flight.getArvDateTime());
        }
        if(flight.getFlightCode() != null){
            existingFlight.get().setFlightCode(flight.getFlightCode());
        }
        if(flight.getAirline() != null){
            existingFlight.get().setAirline(flight.getAirline());
        }
        if(flight.getPrice() != null){
            existingFlight.get().setPrice(flight.getPrice());
        }

        writer.write(flightRepository, DATA_PATH);
        FlightDTO returningFlightDto = modelMapper.map(existingFlight.get(), FlightDTO.class);
        return returningFlightDto;


    }
}
