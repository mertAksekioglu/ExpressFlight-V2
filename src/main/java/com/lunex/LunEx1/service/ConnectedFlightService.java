package com.lunex.LunEx1.service;

import com.google.gson.Gson;
import com.lunex.LunEx1.domain.ConnectedFlight;

import com.lunex.LunEx1.domain.Flight;
import com.lunex.LunEx1.dto.AirportDTO;
import com.lunex.LunEx1.dto.ConnectedFlightDTO;
import com.lunex.LunEx1.dto.FlightSearchRequestDTO;
import com.lunex.LunEx1.repository.IConnectedFlightRepository;
import com.lunex.LunEx1.serviceInterface.IConnectedFlightService;
import com.lunex.LunEx1.util.IWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectedFlightService implements IConnectedFlightService {


    @Autowired
    private IConnectedFlightRepository connectedFlightRepository;


    @Autowired
    private Gson gson;

    @Autowired
    private IWriter writer;

    @Autowired
    private ModelMapper modelMapper;

    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\connectedFlight_data.json";

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

    /*
        @Override
        public ConnectedFlightDTO getConnectedFlightByCode(String connectedFlightCode) {
            Optional<ConnectedFlight> connectedFlight = connectedFlightRepository.findByConnectedFlightCode(connectedFlightCode);
            if(!connectedFlight.isPresent()) {
                throw new IllegalStateException("ConnectedFlight with code " + connectedFlightCode + " does not exist");
            }
            ConnectedFlightDTO returningConnectedFlightDto = modelMapper.map(connectedFlight.get(), ConnectedFlightDTO.class);
            return returningConnectedFlightDto;
        }
    */
    @Override
    public List<ConnectedFlightDTO> searchConnectedFlight(FlightSearchRequestDTO connectedFlightSearchRequestDto) {

/*
        List<ConnectedFlight> allConnectedFlights = connectedFlightRepository.findAll();
        List<ConnectedFlightDTO> resultConnectedFlightDtos = new ArrayList<>();

        for (ConnectedFlight connectedFlight: allConnectedFlights) {
            String firstConnectedFlightDate = connectedFlight.getDepDate();
            String firstDepAirport = connectedFlight.getDepAirport();
            String lastDesAirport = connectedFlight.getArvAirport();

            if(firstDepAirport.equals(connectedFlightSearchRequestDto.getDepAirport()) &&
                    lastDesAirport.equals(connectedFlightSearchRequestDto.getDesAirport()) &&
                    firstConnectedFlightDate.equals(connectedFlightSearchRequestDto.getDepDate())){
                resultConnectedFlightDtos.add(modelMapper.map(connectedFlight,ConnectedFlightDTO.class));
            }

        }

        return resultConnectedFlightDtos;
        */
        return null;
    }



    @Override
    public ConnectedFlightDTO addConnectedFlight(ConnectedFlightDTO connectedFlightDto) {
        ConnectedFlight connectedFlight = modelMapper.map(connectedFlightDto,ConnectedFlight.class);
        Optional<ConnectedFlight> existingConnectedFlight = connectedFlightRepository.findById(connectedFlight.getId());
        if(existingConnectedFlight.isPresent()) {
            throw new IllegalStateException("ConnectedFlight with the id " + connectedFlight.getId()  + "already exists.");
        }
        connectedFlightRepository.save(connectedFlight);
        writer.write(connectedFlightRepository, DATA_PATH);
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
        writer.write(connectedFlightRepository, DATA_PATH);


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

        writer.write(connectedFlightRepository, DATA_PATH);
        ConnectedFlightDTO returningConnectedFlightDto = modelMapper.map(existingConnectedFlight.get(), ConnectedFlightDTO.class);
        return returningConnectedFlightDto;

    }
}
