package com.expressflight.ExpressFlight.demo.demoService;

import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.service.SeatConfigurationService;
import org.springframework.stereotype.Service;

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
