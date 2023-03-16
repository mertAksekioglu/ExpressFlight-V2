package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.dto.FlightSearchRequestDTO;

import java.util.List;

public interface IFlightService {


    public List<FlightDTO> getAllFlights();
    public FlightDTO getFlight(Long flightId);

 //   public FlightDTO getFlightByCode(String flightCode);

    public List<FlightDTO> searchFlight(FlightSearchRequestDTO flightSearchRequestDTO);

    public FlightDTO addFlight(FlightDTO flightDto);
    public FlightDTO deleteFlight(Long flightId);
    public FlightDTO updateFlight(FlightDTO flightDto, Long flightId);

}
