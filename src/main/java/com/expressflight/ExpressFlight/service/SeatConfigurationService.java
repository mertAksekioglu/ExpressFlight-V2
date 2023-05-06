package com.expressflight.ExpressFlight.service;


import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import com.expressflight.ExpressFlight.dto.SeatConfigurationDTO;
import com.expressflight.ExpressFlight.integration.SunExpressIntegration;
import com.expressflight.ExpressFlight.repository.ISeatConfigurationRepository;
import com.expressflight.ExpressFlight.util.seatMapper.SeatMapFactory;
import com.google.gson.Gson;
import com.expressflight.ExpressFlight.serviceInterface.ISeatConfigurationService;
import com.expressflight.ExpressFlight.util.IWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatConfigurationService implements ISeatConfigurationService {

    private final String DATA_PATH = "D:\\Spring MVC Projects\\ExpressFlight\\src\\main\\resources\\seat_configuration_data.json";
    private final boolean UPDATE_JSON = false;

    @Autowired
    private ISeatConfigurationRepository seatConfigurationRepository;

    @Autowired
    private Gson gson;
    @Autowired
    private IWriter writer;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SeatMapFactory seatMapFactory;





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
        writer.write(seatConfigurationRepository, DATA_PATH,UPDATE_JSON);
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
        writer.write(seatConfigurationRepository, DATA_PATH, UPDATE_JSON);
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

        writer.write(seatConfigurationRepository, DATA_PATH, UPDATE_JSON);
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


        //flight.getSeatConfig().getConfigName()).mapSeats()
        return null;
    }




}

