package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Plane;
import com.expressflight.ExpressFlight.dto.PlaneDTO;
import com.expressflight.ExpressFlight.repository.IPlaneRepository;
import com.expressflight.ExpressFlight.serviceInterface.IPlaneService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaneService implements IPlaneService {

    private IPlaneRepository planeRepository;

    private ModelMapper modelMapper;

    public PlaneService(IPlaneRepository planeRepository, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
    }

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
        PlaneDTO returningPlaneDto = modelMapper.map(existingPlane.get(), PlaneDTO.class);
        return returningPlaneDto;
    }

}
