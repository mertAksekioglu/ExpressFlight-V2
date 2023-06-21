package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.ConnectedFlightDTO;
import com.expressflight.ExpressFlight.dto.FlightSearchRequestDTO;

import java.util.List;

public interface IConnectedFlightService {

    public List<ConnectedFlightDTO> getAllConnectedFlights();

    public ConnectedFlightDTO getConnectedFlight(Long connectedFlightId);

    public List<ConnectedFlightDTO> searchConnectedFlight(FlightSearchRequestDTO connectedFlightSearchRequestDTO);

    public ConnectedFlightDTO addConnectedFlight(ConnectedFlightDTO connectedFlightDto);

    public ConnectedFlightDTO deleteConnectedFlight(Long connectedFlightId);

    public ConnectedFlightDTO updateConnectedFlight(ConnectedFlightDTO connectedFlightDto, Long connectedFlightId);

}
