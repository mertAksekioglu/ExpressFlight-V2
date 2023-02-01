package com.lunex.LunEx1.serviceInterface;

import com.lunex.LunEx1.domain.ConnectedFlight;
import com.lunex.LunEx1.dto.ConnectedFlightDTO;
import com.lunex.LunEx1.dto.FlightSearchRequestDTO;
import com.lunex.LunEx1.dto.FlightSearchRequestDTO;

import java.util.List;

public interface IConnectedFlightService {


    public List<ConnectedFlightDTO> getAllConnectedFlights();
    public ConnectedFlightDTO getConnectedFlight(Long connectedFlightId);

    //   public ConnectedFlightDTO getConnectedFlightByCode(String connectedFlightCode);

    public List<ConnectedFlightDTO> searchConnectedFlight(FlightSearchRequestDTO connectedFlightSearchRequestDTO);
    public ConnectedFlightDTO addConnectedFlight(ConnectedFlightDTO connectedFlightDto);
    public ConnectedFlightDTO deleteConnectedFlight(Long connectedFlightId);
    public ConnectedFlightDTO updateConnectedFlight(ConnectedFlightDTO connectedFlightDto, Long connectedFlightId);

}
