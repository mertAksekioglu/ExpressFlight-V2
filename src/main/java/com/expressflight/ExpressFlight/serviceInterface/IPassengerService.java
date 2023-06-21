package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.PassengerDTO;

import java.util.List;

public interface IPassengerService {

    public List<PassengerDTO> getAllPassengers();

    public PassengerDTO getPassenger(Long passengerId);

    public PassengerDTO addPassenger(PassengerDTO passenger);

    public PassengerDTO deletePassenger(Long passengerId);

    public PassengerDTO updatePassenger(PassengerDTO passenger, Long passengerId);
    
}
