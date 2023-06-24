package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.FlightDTO;
import com.expressflight.ExpressFlight.requestdto.FlightRequestDTO;
import com.expressflight.ExpressFlight.requestdto.FlightSearchRequestDTO;

import java.util.List;

public interface IFlightService {

    public List<FlightDTO> getAllFlights();

    public FlightDTO getFlight(Long flightId);

    public List<FlightDTO> getFlightByCode(String flightCode);

    public List<FlightDTO> searchFlight(FlightSearchRequestDTO flightSearchRequestDTO);

    public FlightDTO addFlight(FlightRequestDTO flightRequestDto);

    public FlightDTO deleteFlight(Long flightId);

    public FlightDTO updateFlight(FlightRequestDTO flightRequestDto, Long flightId);

    public List<FlightDTO> configureAllFlightSeats();

}
