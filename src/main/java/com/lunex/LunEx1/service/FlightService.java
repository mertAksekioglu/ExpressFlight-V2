package com.lunex.LunEx1.service;

import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.repository.IFlightRepository;
import com.lunex.LunEx1.repository.IPlaneRepository;
import com.lunex.LunEx1.serviceInterface.IFlightService;
import com.lunex.LunEx1.util.IWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements IFlightService {


    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private IWriter writer;

    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\flight_data.json";

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    @Override
    public Flight getFlight(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalStateException(
                        "Plane with id " + flightId + " does not exist"
                ));;
        return flight;
    }

    @Override
    public void addFlight(Flight flight) {
        Optional<Flight> existingFlight = flightRepository.findByFlightCode(flight.getFlightCode());
        if(existingFlight.isPresent()) {
            throw new IllegalStateException("Flight with the code " + flight.getFlightCode() + "already exists.");
        }
        flightRepository.save(flight);
        writer.write(flightRepository, DATA_PATH);
    }

    @Override
    public void deleteFlight(Long planeId) {
        if(!flightRepository.existsById(planeId)) {
            throw new IllegalStateException("Plane with the id " +planeId + " does not exist");
        }
        flightRepository.deleteById(planeId);
        writer.write(flightRepository, DATA_PATH);
    }

    @Override
    public void updateFlight(Flight flight) {
        Flight existingFlight = flightRepository.findById(flight.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Flight with id " + flight.getId() + " does not exist"));;

        if(flight.getFlightCode() != null){
            existingFlight.setFlightCode(flight.getFlightCode());
        }


        writer.write(flightRepository, DATA_PATH);

    }
}
