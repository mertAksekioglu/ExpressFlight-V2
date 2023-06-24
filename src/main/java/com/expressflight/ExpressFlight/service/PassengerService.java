package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Passenger;
import com.expressflight.ExpressFlight.dto.PassengerDTO;
import com.expressflight.ExpressFlight.repository.IPassengerRepository;
import com.expressflight.ExpressFlight.requestdto.PassengerRequestDTO;
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
            PassengerDTO passengerDto = convertToDTO(passenger);
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
        return convertToDTO(passenger.get());
    }

    @Override
    public PassengerDTO addPassenger(PassengerRequestDTO passengerRequestDto) {
        Passenger passenger = convertToEntity(passengerRequestDto);
        // Add Social Security number to identify
        /*
        Optional<Passenger> existingPassenger = passengerRepository.findByCode(passenger.getCode());
        if(existingPassenger.isPresent()) {
            throw new IllegalStateException("Passenger with the code " + passenger.getCode() + "already exists.");
        }
        */
        passengerRepository.save(passenger);
        return convertToDTO(passenger);
    }

    @Override
    public PassengerDTO deletePassenger(Long passengerId) {
        Optional<Passenger> passenger = passengerRepository.findById(passengerId);
        if(!passenger.isPresent()) {
            throw new IllegalStateException("Passenger with the id " + passengerId + " does not exist");
        }
        passengerRepository.deleteById(passengerId);
        return convertToDTO(passenger.get());
    }

    @Override
    @Transactional
    public PassengerDTO updatePassenger(PassengerRequestDTO passengerRequestDto, Long passengerId) {
        Passenger passenger = convertToEntity(passengerRequestDto);
        Optional<Passenger> existingPassenger = passengerRepository.findById(passengerId);
        if(!existingPassenger.isPresent()) {
            throw new IllegalStateException("Passenger with the id " + existingPassenger.get().getId() + " does not exist.");
        }
        if(passenger.getName() != null){
            existingPassenger.get().setName(passengerRequestDto.getName());
        }
        if(passenger.getSurname() != null){
            existingPassenger.get().setSurname(passenger.getSurname());
        }
        if(passenger.getEmail() != null){
            existingPassenger.get().setEmail(passenger.getEmail());
        }
        if(passenger.getPhone() != null){
            existingPassenger.get().setPhone(passenger.getPhone());
        }
        if(passenger.getGender() != null){
            existingPassenger.get().setGender(passenger.getGender());
        }
        if(passenger.getAge() != null){
            existingPassenger.get().setAge(passenger.getAge());
        }
        if(passenger.getDateOfBirth() != null){
            existingPassenger.get().setDateOfBirth(passenger.getDateOfBirth());
        }
        return convertToDTO(passenger);
    }

    private PassengerDTO convertToDTO(Passenger passenger) {
        PassengerDTO PassengerDto = modelMapper.map(passenger, PassengerDTO.class);
        return PassengerDto;
    }

    private Passenger convertToEntity(PassengerRequestDTO passengerRequestDto) {
        Passenger passenger = modelMapper.map(passengerRequestDto, Passenger.class);
        return passenger;
    }

}
