package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.dto.FlightSearchRequestDTO;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.serviceInterface.IFlightService;
import com.expressflight.ExpressFlight.util.seatMapper.SeatConfigurationFactory;
import com.google.gson.Gson;
import com.expressflight.ExpressFlight.util.IWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements IFlightService {

    private final String DATA_PATH = "D:\\Spring MVC Projects\\ExpressFlight\\src\\main\\resources\\flight_data.json";
    private final boolean UPDATE_JSON = false;

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private Gson gson;
    @Autowired
    private IWriter writer;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SeatConfigurationFactory seatConfigurationFactory;




    @Override
    public List<FlightDTO> getAllFlights() {
        configureFlightSeats();
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
        if(flight.getSeatConfig() != null && flight.getSeatConfig().getSeatConfiguration() == null) {
            SeatConfiguration tempConfig = flight.getSeatConfig();
            tempConfig.setSeatConfiguration(
                    seatConfigurationFactory.createSeatConfiguration(flight.getSeatConfig().getConfigName()).mapSeats());
            flight.setSeatConfig(tempConfig);
        }


        flightRepository.save(flight);
        writer.write(flightRepository, DATA_PATH,UPDATE_JSON);
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
        writer.write(flightRepository, DATA_PATH, UPDATE_JSON);


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
        if(flight.getSeatConfig() != null){
            existingFlight.get().setSeatConfig(flight.getSeatConfig());
        }

        writer.write(flightRepository, DATA_PATH, UPDATE_JSON);
        FlightDTO returningFlightDto = modelMapper.map(existingFlight.get(), FlightDTO.class);
        return returningFlightDto;


    }



    @Override
    public List<FlightDTO> configureFlightSeats() {

        List<Flight> flights = flightRepository.findAll();
        List<FlightDTO> flightDtos = new ArrayList<>();
        List<Flight> unconfiguredFlights = new ArrayList<>();
        for(int i = 0; i < flights.size(); i++){
            System.out.println("#############################################");
            System.out.println("#############################################");
            System.out.println("#############################################");
            System.out.println("#############################################");

            if(flights.get(i).getSeatConfig() != null && flights.get(i).getSeatConfig().getSeatConfiguration() == null) {
                SeatConfiguration tempConfig = flights.get(i).getSeatConfig();
                tempConfig.setSeatConfiguration(
                        seatConfigurationFactory.createSeatConfiguration(flights.get(i).getSeatConfig().getConfigName()).mapSeats());
                flights.get(i).setSeatConfig(tempConfig);
                unconfiguredFlights.add(flights.get(i));
            }

        }
        for (Flight existingFlight : unconfiguredFlights)
        {
            FlightDTO flightDto = modelMapper.map(existingFlight,FlightDTO.class);
            flightDtos.add(flightDto);
        }
        return flightDtos;
    }



}
