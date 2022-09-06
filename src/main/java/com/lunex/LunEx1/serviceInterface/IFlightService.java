package com.lunex.LunEx1.serviceInterface;

import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.dto.FlightDTO;
import com.lunex.LunEx1.dto.FlightSearchRequestDTO;
import com.lunex.LunEx1.dto.FlightSegmentDTO;

import java.util.List;

public interface IFlightService {


    public List<FlightDTO> getAllFlights();
    public FlightDTO getFlight(Long flightId);

    public List<FlightDTO> searchFlight(FlightSearchRequestDTO flightSearchRequestDTO);

    public void addFlight(FlightDTO flightDto);
    public void deleteFlight(Long flightId);
    public void updateFlight(FlightDTO flightDto);

}
