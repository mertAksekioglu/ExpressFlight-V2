package com.lunex.LunEx1.service;

import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.dto.FlightDTO;
import com.lunex.LunEx1.dto.FlightSearchRequestDTO;
import com.lunex.LunEx1.dto.FlightSegmentDTO;
import com.lunex.LunEx1.repository.IFlightRepository;
import com.lunex.LunEx1.serviceInterface.IFlightService;
import com.lunex.LunEx1.util.IWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService implements IFlightService {


    @Autowired
    private IFlightRepository flightRepository;


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
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalStateException(
                        "Flight with id " + flightId + " does not exist"
                ));;
                FlightDTO flightDto = modelMapper.map(flight,FlightDTO.class);
        return flightDto;
    }

    @Override
    public List<FlightDTO> searchFlight(FlightSearchRequestDTO flightSearchRequestDto) {


        List<Flight> allFlights = flightRepository.findAll();
        List<FlightDTO> resultFlightDtos = new ArrayList<>();

        for (Flight flight: allFlights) {
            String firstFlightDate = flight.getFlightSegments()[0].getDepDate();
            String firstDepAirport = flight.getFlightSegments()[0].getDepAirport();
            String lastDesAirport = flight.getFlightSegments()[flight.getFlightSegments().length-1].getDesAirport();
            if(firstDepAirport.equals(flightSearchRequestDto.getDepAirport()) &&
               lastDesAirport.equals(flightSearchRequestDto.getDesAirport()) &&
               firstFlightDate.equals(flightSearchRequestDto.getDepDate())){

                resultFlightDtos.add(modelMapper.map(flight,FlightDTO.class));

            }

        }



        return resultFlightDtos;
    }






    @Override
    public void addFlight(FlightDTO flightDto) {
        Flight flight = modelMapper.map(flightDto,Flight.class);
        /*Optional<Flight> existingFlight = flightRepository.findByFlightSegments(flight.getFlightSegments());
        if(existingFlight.isPresent()) {
            throw new IllegalStateException("Flight with the code " + flight.getFlightSegments().get(0).getFlightCode() + "already exists.");
        }*/
        flightRepository.save(flight);
        writer.write(flightRepository, DATA_PATH);
    }

    @Override
    public void deleteFlight(Long flightId) {
        if(!flightRepository.existsById(flightId)) {
            throw new IllegalStateException("Flight with the id " + flightId + " does not exist");
        }
        flightRepository.deleteById(flightId);
        writer.write(flightRepository, DATA_PATH);
    }

    @Override
    @Transactional
    public void updateFlight(FlightDTO flightDto) {
        Flight flight = modelMapper.map(flightDto,Flight.class);
        Flight existingFlight = flightRepository.findById(flight.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Flight with id " + flight.getId() + " does not exist"));;

        if(flight.getFlightSegments() != null){
            existingFlight.setFlightSegments(flight.getFlightSegments());
        }
        if(flight.getIsConnected() != null){
            existingFlight.setIsConnected(flight.getIsConnected());
        }
        if(flight.getPrice() != null){
            existingFlight.setPrice(flight.getPrice());
        }




        writer.write(flightRepository, DATA_PATH);

    }
}
