package com.expressflight.ExpressFlight.service;


import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.dto.SeatConfigurationDTO;
import com.expressflight.ExpressFlight.repository.ISeatConfigurationRepository;
import com.expressflight.ExpressFlight.util.seatMapper.SeatMapFactory;
import com.expressflight.ExpressFlight.serviceInterface.ISeatConfigurationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
            SeatConfigurationDTO seatConfigurationDto = modelMapper.map(seatConfiguration, SeatConfigurationDTO.class);
            seatConfigurationDtos.add(seatConfigurationDto);
        }
        return seatConfigurationDtos;
    }

    @Override
    public List<SeatConfigurationDTO> getAllUnconfiguredSeatConfigurations() {
        List<SeatConfiguration> seatConfigurations = seatConfigurationRepository.findByIsConfigured(false);
        List<SeatConfigurationDTO> seatConfigurationDtos = new ArrayList<>();
        for (SeatConfiguration seatConfiguration : seatConfigurations) {
            SeatConfigurationDTO seatConfigurationDto = modelMapper.map(seatConfiguration, SeatConfigurationDTO.class);
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
        SeatConfigurationDTO returningSeatConfigurationDto = modelMapper.map(seatConfiguration.get(), SeatConfigurationDTO.class);
        return returningSeatConfigurationDto;
    }

    @Override
    public SeatConfigurationDTO getSeatConfigurationByConfigName(String seatConfigurationConfigName) {
        Optional<SeatConfiguration> seatConfiguration = seatConfigurationRepository.findByConfigName(seatConfigurationConfigName);
        if(!seatConfiguration.isPresent()) {
            throw new IllegalStateException("SeatConfiguration with the code " + seatConfiguration.get().getConfigName() + "does not exist.");
        }
        SeatConfigurationDTO returningSeatConfigurationDto = modelMapper.map(seatConfiguration.get(), SeatConfigurationDTO.class);
        return returningSeatConfigurationDto;
    }

    @Override
    public SeatConfigurationDTO addSeatConfiguration(SeatConfigurationDTO seatConfigurationDto) {
        SeatConfiguration seatConfiguration = modelMapper.map(seatConfigurationDto,SeatConfiguration.class);
        Optional<SeatConfiguration> existingSeatConfiguration = seatConfigurationRepository.findByConfigName(seatConfiguration.getConfigName());
        if(existingSeatConfiguration.isPresent()) {
            throw new IllegalStateException("SeatConfiguration with the code " + seatConfiguration.getConfigName() + "already exists.");
        }
        seatConfigurationRepository.save(seatConfiguration);
        SeatConfigurationDTO returningSeatConfigurationDto = modelMapper.map(seatConfiguration, SeatConfigurationDTO.class);
        return returningSeatConfigurationDto;
    }

    @Override
    public SeatConfigurationDTO deleteSeatConfiguration(Long seatConfigurationId) {
        Optional<SeatConfiguration> seatConfiguration = seatConfigurationRepository.findById(seatConfigurationId);
        if(!seatConfiguration.isPresent()) {
            throw new IllegalStateException("SeatConfiguration with the id " + seatConfigurationId + " does not exist");
        }
        seatConfigurationRepository.deleteById(seatConfigurationId);
        SeatConfigurationDTO returningSeatConfigurationDto = modelMapper.map(seatConfiguration.get(), SeatConfigurationDTO.class);
        return returningSeatConfigurationDto;
    }

    @Override
    @Transactional
    public SeatConfigurationDTO updateSeatConfiguration(SeatConfigurationDTO seatConfigurationDto, Long seatConfigurationId) {
        SeatConfiguration seatConfiguration = modelMapper.map(seatConfigurationDto,SeatConfiguration.class);
        Optional<SeatConfiguration> existingSeatConfiguration = seatConfigurationRepository.findById(seatConfigurationId);
        if(!existingSeatConfiguration.isPresent()) {
            throw new IllegalStateException("SeatConfiguration with the code " + existingSeatConfiguration.get().getConfigName() + " does not exist.");
        }
        if(seatConfiguration.getConfigName() != null){
            existingSeatConfiguration.get().setConfigName(seatConfigurationDto.getConfigName());
        }
        if(seatConfiguration.getConfigPlane() != null){
            existingSeatConfiguration.get().setConfigPlane(seatConfigurationDto.getConfigName());
        }
        if(seatConfiguration.getConfigPlane() != null){
            existingSeatConfiguration.get().setConfigPlane(seatConfigurationDto.getConfigName());
        }
        if(seatConfiguration.getSeatMap() != null){
            existingSeatConfiguration.get().setSeatMap(seatConfigurationDto.getSeatMap());
        }
        SeatConfigurationDTO returningSeatConfigurationDto = modelMapper.map(existingSeatConfiguration.get(), SeatConfigurationDTO.class);
        return returningSeatConfigurationDto;

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
        SeatConfigurationDTO returningSeatConfigurationDTO = modelMapper.map(seatConfig,SeatConfigurationDTO.class);
        return returningSeatConfigurationDTO;
    }

    @Override
    @Transactional
    public List<SeatConfigurationDTO> configureAllUnconfiguredSeatConfigurations() {
        List<SeatConfiguration> unconfiguredConfigurations = seatConfigurationRepository.findByIsConfigured(false);
        List<SeatConfigurationDTO> returningSeatConfigurationDtos = new ArrayList<>();
        for (SeatConfiguration seatConfig : unconfiguredConfigurations) {
            seatConfig.setSeatMap(seatMapFactory.createSeatMap(seatConfig.getConfigName()).mapSeats());
            seatConfig.setIsConfigured(true);

            SeatConfigurationDTO seatConfigurationDTO = modelMapper.map(seatConfig,SeatConfigurationDTO.class);
            returningSeatConfigurationDtos.add(seatConfigurationDTO);
        }
        return returningSeatConfigurationDtos;
    }

}

