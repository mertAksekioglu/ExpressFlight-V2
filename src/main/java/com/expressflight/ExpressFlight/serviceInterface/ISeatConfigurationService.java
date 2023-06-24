package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.SeatConfigurationDTO;
import com.expressflight.ExpressFlight.requestdto.SeatConfigurationRequestDTO;

import java.util.List;

public interface ISeatConfigurationService {

    public List<SeatConfigurationDTO> getAllSeatConfigurations();

    public List<SeatConfigurationDTO> getAllUnconfiguredSeatConfigurations();

    public SeatConfigurationDTO getSeatConfiguration(Long seatConfigurationId);

    public SeatConfigurationDTO getSeatConfigurationByConfigName(String seatConfigurationConfigName);

    public SeatConfigurationDTO addSeatConfiguration(SeatConfigurationRequestDTO seatConfigurationRequestDto);

    public SeatConfigurationDTO deleteSeatConfiguration(Long seatConfigurationId);

    public SeatConfigurationDTO updateSeatConfiguration(SeatConfigurationRequestDTO seatConfigurationRequestDto, Long seatConfigurationId);

    public SeatConfigurationDTO configureSeatConfiguration(Long seatConfigurationId);

    public List<SeatConfigurationDTO> configureAllUnconfiguredSeatConfigurations();

}
