package com.lunex.LunEx1.serviceInterface;

import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.dto.FlightDTO;
import com.lunex.LunEx1.dto.FlightSearchRequestDTO;
import com.lunex.LunEx1.dto.FlightSegmentDTO;

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
