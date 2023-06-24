package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.PassengerDTO;
import com.expressflight.ExpressFlight.requestdto.PassengerRequestDTO;

import java.util.List;

public interface IPassengerService {

    public List<PassengerDTO> getAllPassengers();

    public PassengerDTO getPassenger(Long passengerId);

    public PassengerDTO addPassenger(PassengerRequestDTO passengerRequestDto);

    public PassengerDTO deletePassenger(Long passengerId);

    public PassengerDTO updatePassenger(PassengerRequestDTO passengerRequestDto, Long passengerId);
    
}
