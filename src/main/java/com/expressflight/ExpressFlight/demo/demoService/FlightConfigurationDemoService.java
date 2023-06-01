package com.expressflight.ExpressFlight.demo.demoService;

import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.repository.ISeatConfigurationRepository;
import com.expressflight.ExpressFlight.service.SeatConfigurationService;
import com.expressflight.ExpressFlight.util.seatMapper.SeatMapFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightConfigurationDemoService {

    IFlightRepository flightRepository;

    SeatConfigurationService seatConfigurationService;


    public FlightConfigurationDemoService(IFlightRepository flightRepository, SeatConfigurationService seatConfigurationService) {
        this.flightRepository = flightRepository;
        this.seatConfigurationService = seatConfigurationService;
    }

    public void configureFlightSeats(Long flightId) {
        Flight flight = flightRepository.findById(flightId).get();
        seatConfigurationService.configureSeatConfiguration(flight.getSeatConfig());
    }

    public void configureAllFlightSeats() {
        seatConfigurationService.configureAllUnconfiguredSeatConfigurations();
    }

}
