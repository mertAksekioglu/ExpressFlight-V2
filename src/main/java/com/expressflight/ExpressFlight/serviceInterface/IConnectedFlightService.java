package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.ConnectedFlightDTO;
import com.expressflight.ExpressFlight.requestdto.ConnectedFlightRequestDTO;
import com.expressflight.ExpressFlight.requestdto.FlightSearchRequestDTO;

import java.util.List;

public interface IConnectedFlightService {

    public List<ConnectedFlightDTO> getAllConnectedFlights();

    public ConnectedFlightDTO getConnectedFlight(Long connectedFlightId);

    public List<ConnectedFlightDTO> searchConnectedFlight(FlightSearchRequestDTO connectedFlightSearchRequestDTO);

    public ConnectedFlightDTO addConnectedFlight(ConnectedFlightRequestDTO connectedFlightRequestDto);

    public ConnectedFlightDTO deleteConnectedFlight(Long connectedFlightId);

    public ConnectedFlightDTO updateConnectedFlight(ConnectedFlightRequestDTO connectedFlightRequestDto, Long connectedFlightId);

}
