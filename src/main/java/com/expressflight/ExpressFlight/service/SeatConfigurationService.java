package com.expressflight.ExpressFlight.service;


import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.dto.SeatConfigurationDTO;
import com.expressflight.ExpressFlight.repository.ISeatConfigurationRepository;
import com.expressflight.ExpressFlight.requestdto.SeatConfigurationRequestDTO;
import com.expressflight.ExpressFlight.serviceInterface.ISeatConfigurationService;
import com.expressflight.ExpressFlight.util.seatMapper.SeatMapFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatConfigurationService implements ISeatConfigurationService {

    private ISeatConfigurationRepository seatConfigurationRepository;

    private ModelMapper modelMapper;

    private SeatMapFactory seatMapFactory;

    public SeatConfigurationService(ISeatConfigurationRepository seatConfigurationRepository,
                                    ModelMapper modelMapper, SeatMapFactory seatMapFactory) {
        this.seatConfigurationRepository = seatConfigurationRepository;
        this.modelMapper = modelMapper;
        this.seatMapFactory = seatMapFactory;
    }

    @Override
    public List<SeatConfigurationDTO> getAllSeatConfigurations() {
        List<SeatConfiguration> seatConfigurations = seatConfigurationRepository.findAll();
        List<SeatConfigurationDTO> seatConfigurationDtos = new ArrayList<>();
        for (SeatConfiguration seatConfiguration : seatConfigurations) {
            SeatConfigurationDTO seatConfigurationDto = convertToDTO(seatConfiguration);
            seatConfigurationDtos.add(seatConfigurationDto);
        }
        return seatConfigurationDtos;
    }

    @Override
    public List<SeatConfigurationDTO> getAllUnconfiguredSeatConfigurations() {
        List<SeatConfiguration> seatConfigurations = seatConfigurationRepository.findByIsConfigured(false);
        List<SeatConfigurationDTO> seatConfigurationDtos = new ArrayList<>();
        for (SeatConfiguration seatConfiguration : seatConfigurations) {
            SeatConfigurationDTO seatConfigurationDto = convertToDTO(seatConfiguration);
            seatConfigurationDtos.add(seatConfigurationDto);
        }
        return seatConfigurationDtos;
    }

    @Override
    public SeatConfigurationDTO getSeatConfiguration(Long seatConfigurationId) {
        Optional<SeatConfiguration> seatConfiguration = seatConfigurationRepository.findById(seatConfigurationId);
        if(!seatConfiguration.isPresent()) {
            throw new IllegalStateException("SeatConfiguration with the id " + seatConfigurationId + " does not exist");
        }
        return convertToDTO(seatConfiguration.get());
    }

    @Override
    public SeatConfigurationDTO getSeatConfigurationByConfigName(String seatConfigurationConfigName) {
        Optional<SeatConfiguration> seatConfiguration = seatConfigurationRepository.findByConfigName(seatConfigurationConfigName);
        if(!seatConfiguration.isPresent()) {
            throw new IllegalStateException("SeatConfiguration with the code " + seatConfiguration.get().getConfigName() + "does not exist.");
        }
        return convertToDTO(seatConfiguration.get());
    }

    @Override
    public SeatConfigurationDTO addSeatConfiguration(SeatConfigurationRequestDTO seatConfigurationRequestDto) {
        SeatConfiguration seatConfiguration = convertToEntity(seatConfigurationRequestDto);
        Optional<SeatConfiguration> existingSeatConfiguration = seatConfigurationRepository.findByConfigName(seatConfiguration.getConfigName());
        if(existingSeatConfiguration.isPresent()) {
            throw new IllegalStateException("SeatConfiguration with the code " + seatConfiguration.getConfigName() + "already exists.");
        }
        seatConfigurationRepository.save(seatConfiguration);
        return convertToDTO(seatConfiguration);
    }

    @Override
    public SeatConfigurationDTO deleteSeatConfiguration(Long seatConfigurationId) {
        Optional<SeatConfiguration> seatConfiguration = seatConfigurationRepository.findById(seatConfigurationId);
        if(!seatConfiguration.isPresent()) {
            throw new IllegalStateException("SeatConfiguration with the id " + seatConfigurationId + " does not exist");
        }
        seatConfigurationRepository.deleteById(seatConfigurationId);
        return convertToDTO(seatConfiguration.get());
    }

    @Override
    @Transactional
    public SeatConfigurationDTO updateSeatConfiguration(SeatConfigurationRequestDTO seatConfigurationRequestDto, Long seatConfigurationId) {
        SeatConfiguration seatConfiguration = convertToEntity(seatConfigurationRequestDto);
        Optional<SeatConfiguration> existingSeatConfiguration = seatConfigurationRepository.findById(seatConfigurationId);
        if(!existingSeatConfiguration.isPresent()) {
            throw new IllegalStateException("SeatConfiguration with the code " + existingSeatConfiguration.get().getConfigName() + " does not exist.");
        }
        if(seatConfiguration.getConfigName() != null){
            existingSeatConfiguration.get().setConfigName(seatConfiguration.getConfigName());
        }
        if(seatConfiguration.getConfigPlane() != null){
            existingSeatConfiguration.get().setConfigPlane(seatConfiguration.getConfigName());
        }
        if(seatConfiguration.getConfigPlane() != null){
            existingSeatConfiguration.get().setConfigPlane(seatConfiguration.getConfigName());
        }
        if(seatConfiguration.getSeatMap() != null){
            existingSeatConfiguration.get().setSeatMap(seatConfiguration.getSeatMap());
        }
        return convertToDTO(existingSeatConfiguration.get());
    }

    @Override
    @Transactional
    public SeatConfigurationDTO configureSeatConfiguration(Long seatConfigurationId) {
        SeatConfiguration seatConfig = seatConfigurationRepository.findById(seatConfigurationId).get();
        if(seatConfig.getIsConfigured() == true){
            System.out.println("SEAT ALREADY CONFIGURED");
        }
        else {
            seatConfig.setSeatMap(seatMapFactory.createSeatMap(seatConfig.getConfigName()).mapSeats());
            seatConfig.setIsConfigured(true);
        }
        return convertToDTO(seatConfig);
    }

    @Override
    @Transactional
    public List<SeatConfigurationDTO> configureAllUnconfiguredSeatConfigurations() {
        List<SeatConfiguration> unconfiguredConfigurations = seatConfigurationRepository.findByIsConfigured(false);
        List<SeatConfigurationDTO> returningSeatConfigurationDtos = new ArrayList<>();
        for (SeatConfiguration seatConfig : unconfiguredConfigurations) {
            seatConfig.setSeatMap(seatMapFactory.createSeatMap(seatConfig.getConfigName()).mapSeats());
            seatConfig.setIsConfigured(true);

            SeatConfigurationDTO seatConfigurationDTO = convertToDTO(seatConfig);
            returningSeatConfigurationDtos.add(seatConfigurationDTO);
        }
        return returningSeatConfigurationDtos;
    }

    private SeatConfigurationDTO convertToDTO(SeatConfiguration seatConfiguration) {
        SeatConfigurationDTO SeatConfigurationDto = modelMapper.map(seatConfiguration, SeatConfigurationDTO.class);
        return SeatConfigurationDto;
    }

    private SeatConfiguration convertToEntity(SeatConfigurationRequestDTO seatConfigurationRequestDto) {
        SeatConfiguration seatConfiguration = modelMapper.map(seatConfigurationRequestDto, SeatConfiguration.class);
        return seatConfiguration;
    }

}

