package com.lunex.LunEx1.serviceInterface;

import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.domain.Plane;

import java.util.List;

public interface IFlightService {


    public List<Flight> getAllFlights();
    public Flight getFlight(Long flightId);
    public void addFlight(Flight flight);
    public void deleteFlight(Long flightId);
    public void updateFlight(Flight flight);

}
