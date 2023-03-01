package com.lunex.LunEx1.service;

import com.google.gson.Gson;
import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.dto.FlightDTO;
import com.lunex.LunEx1.dto.PlaneDTO;
import com.lunex.LunEx1.integration.SunExpressIntegration;
import com.lunex.LunEx1.repository.IPlaneRepository;
import com.lunex.LunEx1.serviceInterface.IPlaneService;
import com.lunex.LunEx1.util.IWriter;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaneService implements IPlaneService {

    @Autowired
    private IPlaneRepository planeRepository;

    @Autowired
    private Gson gson;
    @Autowired
    private IWriter writer;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SunExpressIntegration xqintegration;



    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\plane_data.json";

    @Override
    public List<PlaneDTO> getAllPlanes() {
         List<Plane> planes = planeRepository.findAll();
         List<PlaneDTO> planeDtos = new ArrayList<>();

        for (Plane plane : planes) {
            PlaneDTO planeDto = modelMapper.map(plane, PlaneDTO.class);
            planeDtos.add(planeDto);
        }


        xqintegration.getData();
        xqintegration.addData();






         return planeDtos;
    }
    @Override
    public PlaneDTO getPlane(Long planeId) {
        Optional<Plane> plane = planeRepository.findById(planeId);
        if(!plane.isPresent()) {
            throw new IllegalStateException("Plane with the id " + planeId + " does not exist");
        }
        PlaneDTO returningPlaneDto = modelMapper.map(plane.get(), PlaneDTO.class);
        return returningPlaneDto;
    }


    @Override
    public PlaneDTO getPlaneByCode(String planeCode) {
        Optional<Plane> plane = planeRepository.findByCode(planeCode);
        if(!plane.isPresent()) {
            throw new IllegalStateException("Plane with the code " + plane.get().getCode() + "does not exist.");
        }
        PlaneDTO returningPlaneDto = modelMapper.map(plane.get(), PlaneDTO.class);
        return returningPlaneDto;
    }




    @Override
    public PlaneDTO addPlane(PlaneDTO planeDto) {
        Plane plane = modelMapper.map(planeDto,Plane.class);
        Optional<Plane> existingPlane = planeRepository.findByCode(plane.getCode());
        if(existingPlane.isPresent()) {
            throw new IllegalStateException("Plane with the code " + plane.getCode() + "already exists.");
        }
        planeRepository.save(plane);
        writer.write(planeRepository, DATA_PATH);
        PlaneDTO returningPlaneDto = modelMapper.map(plane, PlaneDTO.class);
        return returningPlaneDto;
    }

    @Override
    public PlaneDTO deletePlane(Long planeId) {
        Optional<Plane> plane = planeRepository.findById(planeId);
        if(!plane.isPresent()) {
            throw new IllegalStateException("Plane with the id " + planeId + " does not exist");
        }
        planeRepository.deleteById(planeId);
        writer.write(planeRepository, DATA_PATH);
        PlaneDTO returningPlaneDto = modelMapper.map(plane.get(), PlaneDTO.class);
        return returningPlaneDto;
    }

    @Override
    @Transactional
    public PlaneDTO updatePlane(PlaneDTO planeDto, Long planeId) {
        Plane plane = modelMapper.map(planeDto,Plane.class);
        Optional<Plane> existingPlane = planeRepository.findById(planeId);
        if(!existingPlane.isPresent()) {
            throw new IllegalStateException("Plane with the code " + existingPlane.get().getCode() + " does not exist.");
        }
        if(plane.getCode() != null){
            existingPlane.get().setCode(planeDto.getCode());
        }
        if(plane.getModel() != null){
            existingPlane.get().setModel(planeDto.getModel());
        }
        if(plane.getYearOfProduction() != null){
            existingPlane.get().setYearOfProduction(planeDto.getYearOfProduction());
        }

        writer.write(planeRepository, DATA_PATH);
        PlaneDTO returningPlaneDto = modelMapper.map(existingPlane.get(), PlaneDTO.class);
        return returningPlaneDto;

    }

}
