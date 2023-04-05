package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.SeatConfigurationDTO;

import java.util.List;

public interface ISeatConfigurationService {

    public List<SeatConfigurationDTO> getAllSeatConfigurations();
    public SeatConfigurationDTO getSeatConfiguration(Long seatConfigurationId);
    public SeatConfigurationDTO getSeatConfigurationByConfigName(String seatConfigurationConfigName);
    public SeatConfigurationDTO addSeatConfiguration(SeatConfigurationDTO seatConfiguration);
    public SeatConfigurationDTO deleteSeatConfiguration(Long seatConfigurationId);
    public SeatConfigurationDTO updateSeatConfiguration(SeatConfigurationDTO seatConfiguration, Long seatConfigurationId);

}
