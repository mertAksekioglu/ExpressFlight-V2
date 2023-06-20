package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.ConnectedFlight;
import com.expressflight.ExpressFlight.domain.Flight;
import com.expressflight.ExpressFlight.dto.ConnectedFlightDTO;
import com.expressflight.ExpressFlight.dto.FlightSearchRequestDTO;
import com.expressflight.ExpressFlight.repository.IConnectedFlightRepository;
import com.expressflight.ExpressFlight.repository.IFlightRepository;
import com.expressflight.ExpressFlight.serviceInterface.IConnectedFlightService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectedFlightService implements IConnectedFlightService {

    private IConnectedFlightRepository connectedFlightRepository;

    private IFlightRepository flightRepository;

    private ModelMapper modelMapper;

    public ConnectedFlightService(IConnectedFlightRepository connectedFlightRepository,
                                  IFlightRepository flightRepository, ModelMapper modelMapper) {
        this.connectedFlightRepository = connectedFlightRepository;
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ConnectedFlightDTO> getAllConnectedFlights() {
        List<ConnectedFlight> connectedFlights = connectedFlightRepository.findAll();
        List<ConnectedFlightDTO> connectedFlightDtos = new ArrayList<>();
        for (ConnectedFlight existingConnectedFlight : connectedFlights)
        {
            ConnectedFlightDTO connectedFlightDto = modelMapper.map(existingConnectedFlight,ConnectedFlightDTO.class);
            connectedFlightDtos.add(connectedFlightDto);
        }
        return connectedFlightDtos;
    }
    @Override
    public ConnectedFlightDTO getConnectedFlight(Long connectedFlightId) {
        Optional<ConnectedFlight> connectedFlight = connectedFlightRepository.findById(connectedFlightId);
        if(!connectedFlight.isPresent()) {
            throw new IllegalStateException("ConnectedFlight with id " + connectedFlightId + " does not exist");
        }
        ConnectedFlightDTO returningConnectedFlightDto = modelMapper.map(connectedFlight.get(), ConnectedFlightDTO.class);
        return returningConnectedFlightDto;
    }

    @Override
    public List<ConnectedFlightDTO> searchConnectedFlight(FlightSearchRequestDTO connectedFlightSearchRequestDto) {
        List<ConnectedFlight> allConnectedFlights = connectedFlightRepository.findAll();
        List<ConnectedFlightDTO> resultConnectedFlightDtos = new ArrayList<>();
        for (ConnectedFlight connectedFlight: allConnectedFlights) {
            Flight firstFlight = flightRepository.findById(connectedFlight.getFlightLegs()[0]).get();
            Flight lastFlight = flightRepository.findById(
                    connectedFlight.getFlightLegs()[connectedFlight.getFlightLegs().length-1]).get();
            LocalDate firstConnectedFlightDate = firstFlight.getDepDateTime().toLocalDate();
            Long firstDepAirport = firstFlight.getDepAirport();
            Long lastDesAirport = lastFlight.getDepAirport();
            if(firstDepAirport.equals(connectedFlightSearchRequestDto.getDepAirport()) &&
                    lastDesAirport.equals(connectedFlightSearchRequestDto.getDesAirport()) &&
                    firstConnectedFlightDate.equals(connectedFlightSearchRequestDto.getDepDateTime().toLocalDate())){
                resultConnectedFlightDtos.add(modelMapper.map(connectedFlight,ConnectedFlightDTO.class));
            }
        }
        return resultConnectedFlightDtos;
    }

    @Override
    public ConnectedFlightDTO addConnectedFlight(ConnectedFlightDTO connectedFlightDto) {
        ConnectedFlight connectedFlight = modelMapper.map(connectedFlightDto,ConnectedFlight.class);
        Optional<ConnectedFlight> existingConnectedFlight = connectedFlightRepository.findById(connectedFlight.getId());
        // TODO How do we know the existingConnectedFlight has the correct id ? We cant know that
        if(existingConnectedFlight.isPresent()) {
            throw new IllegalStateException("ConnectedFlight with the id " + connectedFlight.getId()  + "already exists.");
        }
        connectedFlightRepository.save(connectedFlight);
        ConnectedFlightDTO returningConnectedFlightDto = modelMapper.map(connectedFlight, ConnectedFlightDTO.class);
        return returningConnectedFlightDto;
    }

    @Override
    public ConnectedFlightDTO deleteConnectedFlight(Long connectedFlightId) {
        Optional<ConnectedFlight> connectedFlight = connectedFlightRepository.findById(connectedFlightId);
        if(!connectedFlight.isPresent()) {
            throw new IllegalStateException("ConnectedFlight with the id " + connectedFlightId + " does not exist");
        }
        connectedFlightRepository.deleteById(connectedFlightId);
        ConnectedFlightDTO returningConnectedFlightDto = modelMapper.map(connectedFlight.get(), ConnectedFlightDTO.class);
        return returningConnectedFlightDto;
    }

    @Override
    @Transactional
    public ConnectedFlightDTO updateConnectedFlight(ConnectedFlightDTO connectedFlightDto, Long connectedFlightId) {
        ConnectedFlight connectedFlight = modelMapper.map(connectedFlightDto,ConnectedFlight.class);
        Optional<ConnectedFlight> existingConnectedFlight = connectedFlightRepository.findById(connectedFlightId);
        if(!existingConnectedFlight.isPresent()) {
            throw new IllegalStateException("ConnectedFlight with the id " + existingConnectedFlight.get().getId() + " does not exist");
        }
        if(connectedFlight.getFlightLegs() != null){
            existingConnectedFlight.get().setFlightLegs(connectedFlight.getFlightLegs());
        }
        if(connectedFlight.getStopCount() != null){
            existingConnectedFlight.get().setStopCount(connectedFlight.getStopCount());
        }
        if(connectedFlight.getPrice() != null){
            existingConnectedFlight.get().setPrice(connectedFlight.getPrice());
        }
        if(connectedFlight.getLayoverMinutes() != null){
            existingConnectedFlight.get().setLayoverMinutes(connectedFlight.getLayoverMinutes());
        }
        ConnectedFlightDTO returningConnectedFlightDto = modelMapper.map(existingConnectedFlight.get(), ConnectedFlightDTO.class);
        return returningConnectedFlightDto;
    }

}
