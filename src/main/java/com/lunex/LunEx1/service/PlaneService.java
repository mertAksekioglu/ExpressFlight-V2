package com.lunex.LunEx1.service;

import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.dto.PlaneDTO;
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
    private IWriter writer;

    @Autowired
    private ModelMapper modelMapper;

    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\plane_data.json";

    @Override
    public List<PlaneDTO> getAllPlanes() {
         List<Plane> planes = planeRepository.findAll();
         List<PlaneDTO> planeDtos = new ArrayList<>();

        for (Plane plane : planes) {
            PlaneDTO planeDto = modelMapper.map(plane, PlaneDTO.class);
            planeDtos.add(planeDto);
        }

         return planeDtos;
    }
    @Override
    public PlaneDTO getPlane(Long planeId) {
        Plane plane = planeRepository.findById(planeId)
                .orElseThrow(() -> new IllegalStateException(
                "Plane with id " + planeId + " does not exist"
        ));;
        PlaneDTO planeDto = modelMapper.map(plane, PlaneDTO.class);
        return planeDto;
    }


    @Override
    public PlaneDTO getPlaneByCode(String planeCode) {
        Plane plane = planeRepository.findByCode(planeCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Plane with id " + planeCode + " does not exist"
                ));;
        PlaneDTO planeDto = modelMapper.map(plane, PlaneDTO.class);
        return planeDto;
    }

    @Override
    public void addPlane(PlaneDTO planeDto) {
        Plane plane = modelMapper.map(planeDto,Plane.class);
        Optional<Plane> existingPlane = planeRepository.findByCode(plane.getCode());
        if(existingPlane.isPresent()) {
            throw new IllegalStateException("Plane with the code " + plane.getCode() + "already exists.");
        }
        planeRepository.save(plane);
        writer.write(planeRepository, DATA_PATH);
    }

    @Override
    public void deletePlane(Long planeId) {
        if(!planeRepository.existsById(planeId)) {
            throw new IllegalStateException("Plane with the id " +planeId + " does not exist");
        }
        planeRepository.deleteById(planeId);
        writer.write(planeRepository, DATA_PATH);
    }

    @Override
    @Transactional
    public void updatePlane(PlaneDTO planeDto) {
        Plane plane = modelMapper.map(planeDto,Plane.class);
        Plane existingPlane = planeRepository.findById(plane.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Plane with id " + plane.getId() + " does not exist"));;
        if(plane.getCode() != null){
            existingPlane.setCode(plane.getCode());
        }
        if(plane.getModel() != null){
            existingPlane.setModel(plane.getModel());
        }
        if(plane.getYearOfProduction() != null){
            existingPlane.setYearOfProduction(plane.getYearOfProduction());
        }

        writer.write(planeRepository, DATA_PATH);

    }

}
