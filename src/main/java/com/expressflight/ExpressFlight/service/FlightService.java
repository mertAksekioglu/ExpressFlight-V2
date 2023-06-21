package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.dto.FlightSearchRequestDTO;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.repository.ISeatConfigurationRepository;
import com.expressflight.ExpressFlight.serviceInterface.IFlightService;
import com.expressflight.ExpressFlight.util.seatMapper.SeatMapFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements IFlightService {

    private IFlightRepository flightRepository;

    private ISeatConfigurationRepository seatConfigurationRepository;

    private SeatConfigurationService seatConfigurationService;

    private ModelMapper modelMapper;

    private SeatMapFactory seatMapFactory;

    public FlightService(IFlightRepository flightRepository, ISeatConfigurationRepository seatConfigurationRepository,
                         SeatConfigurationService seatConfigurationService, ModelMapper modelMapper,
                         SeatMapFactory seatMapFactory) {
        this.flightRepository = flightRepository;
        this.seatConfigurationRepository = seatConfigurationRepository;
        this.seatConfigurationService = seatConfigurationService;
        this.modelMapper = modelMapper;
        this.seatMapFactory = seatMapFactory;
    }

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
        checkFlightExistence(flightId);
        return convertFlightToDTO(flight.get());
    }

    @Override
    public List<FlightDTO> getFlightByCode(String flightCode) {
        // TODO Check the validity of the code.
        List<Flight> flights = flightRepository.findByFlightCode(flightCode);
        List<FlightDTO> flightDtos = new ArrayList<>();
        for (Flight existingFlight : flights)
        {
            FlightDTO flightDto = modelMapper.map(existingFlight,FlightDTO.class);
            flightDtos.add(flightDto);
        }
        return flightDtos;
    }

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
        return convertFlightToDTO(flight);
    }

    @Override
    public FlightDTO deleteFlight(Long flightId) {
        checkFlightExistence(flightId);
        flightRepository.deleteById(flightId);
        return convertFlightToDTO(flightRepository.findById(flightId).get());
    }

    @Override
    @Transactional
    public FlightDTO updateFlight(FlightDTO flightDto, Long flightId) {
        Flight flight = modelMapper.map(flightDto,Flight.class);
        Optional<Flight> existingFlight = flightRepository.findById(flightId);
        checkFlightExistence(flightId);
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
        if(flight.getSeatConfig() != null){
            existingFlight.get().setSeatConfig(flight.getSeatConfig());
        }
        return convertFlightToDTO(existingFlight.get());
    }

    @Override
    public List<FlightDTO> configureAllFlightSeats() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightDTO> flightDtos = new ArrayList<>();
        List<Flight> unconfiguredFlights = new ArrayList<>();
        for (Flight existingFlight : unconfiguredFlights)
        {
            FlightDTO flightDto = modelMapper.map(existingFlight,FlightDTO.class);
            flightDtos.add(flightDto);
        }
        return flightDtos;
    }

    public void checkFlightExistence(Long flightId) {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if(!flight.isPresent()) {
            throw new IllegalStateException("Flight with the id " + flightId + " does not exist");
        }
    }

    public FlightDTO convertFlightToDTO(Flight flight) {
        FlightDTO returningFlightDto = modelMapper.map(flight, FlightDTO.class);
        return returningFlightDto;
    }

}
