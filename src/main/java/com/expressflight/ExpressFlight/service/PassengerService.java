package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Passenger;
import com.expressflight.ExpressFlight.dto.PassengerDTO;
import com.expressflight.ExpressFlight.repository.IPassengerRepository;
import com.expressflight.ExpressFlight.serviceInterface.IPassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService implements IPassengerService {

    private IPassengerRepository passengerRepository;

    private ModelMapper modelMapper;

    public PassengerService(IPassengerRepository passengerRepository, ModelMapper modelMapper) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PassengerDTO> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        List<PassengerDTO> passengerDtos = new ArrayList<>();
        for (Passenger passenger : passengers) {
            PassengerDTO passengerDto = modelMapper.map(passenger, PassengerDTO.class);
            passengerDtos.add(passengerDto);
        }
        return passengerDtos;
    }

    @Override
    public PassengerDTO getPassenger(Long passengerId) {
        Optional<Passenger> passenger = passengerRepository.findById(passengerId);
        if(!passenger.isPresent()) {
            throw new IllegalStateException("Passenger with the id " + passengerId + " does not exist");
        }
        PassengerDTO returningPassengerDto = modelMapper.map(passenger.get(), PassengerDTO.class);
        return returningPassengerDto;
    }

    @Override
    public PassengerDTO addPassenger(PassengerDTO passengerDto) {
        Passenger passenger = modelMapper.map(passengerDto,Passenger.class);
        // Add Social Security number to identify
        /*
        Optional<Passenger> existingPassenger = passengerRepository.findByCode(passenger.getCode());
        if(existingPassenger.isPresent()) {
            throw new IllegalStateException("Passenger with the code " + passenger.getCode() + "already exists.");
        }
        */
        passengerRepository.save(passenger);
        PassengerDTO returningPassengerDto = modelMapper.map(passenger, PassengerDTO.class);
        return returningPassengerDto;
    }

    @Override
    public PassengerDTO deletePassenger(Long passengerId) {
        Optional<Passenger> passenger = passengerRepository.findById(passengerId);
        if(!passenger.isPresent()) {
            throw new IllegalStateException("Passenger with the id " + passengerId + " does not exist");
        }
        passengerRepository.deleteById(passengerId);
        PassengerDTO returningPassengerDto = modelMapper.map(passenger.get(), PassengerDTO.class);
        return returningPassengerDto;
    }

    @Override
    @Transactional
    public PassengerDTO updatePassenger(PassengerDTO passengerDto, Long passengerId) {
        Passenger passenger = modelMapper.map(passengerDto,Passenger.class);
        Optional<Passenger> existingPassenger = passengerRepository.findById(passengerId);
        if(!existingPassenger.isPresent()) {
            throw new IllegalStateException("Passenger with the id " + existingPassenger.get().getId() + " does not exist.");
        }
        if(passenger.getName() != null){
            existingPassenger.get().setName(passengerDto.getName());
        }
        if(passenger.getSurname() != null){
            existingPassenger.get().setSurname(passengerDto.getSurname());
        }
        if(passenger.getEmail() != null){
            existingPassenger.get().setEmail(passengerDto.getEmail());
        }
        if(passenger.getPhone() != null){
            existingPassenger.get().setPhone(passengerDto.getPhone());
        }
        if(passenger.getGender() != null){
            existingPassenger.get().setGender(passengerDto.getGender());
        }
        if(passenger.getAge() != null){
            existingPassenger.get().setAge(passengerDto.getAge());
        }
        if(passenger.getDateOfBirth() != null){
            existingPassenger.get().setDateOfBirth(passengerDto.getDateOfBirth());
        }
        PassengerDTO returningPassengerDto = modelMapper.map(existingPassenger.get(), PassengerDTO.class);
        return returningPassengerDto;
    }

}
