package com.lunex.LunEx1.service;

import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.repository.IPlaneRepository;
import com.lunex.LunEx1.serviceInterface.IPlaneService;
import com.lunex.LunEx1.util.IWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PlaneService implements IPlaneService {

    @Autowired
    private IPlaneRepository planeRepository;

    @Autowired
    private IWriter writer;

    private final String DATA_PATH = "D:\\Spring MVC Projects\\LunEx1\\src\\main\\resources\\plane_data.json";

    @Override
    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }
    @Override
    public Plane getPlane(Long planeId) {
        Plane plane = planeRepository.findById(planeId)
                .orElseThrow(() -> new IllegalStateException(
                "Plane with id " + planeId + " does not exist"
        ));;
        return plane;
    }

    @Override
    public void addPlane(Plane plane) {
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
    public void updatePlane(Plane plane) {
        Plane existingPlane = planeRepository.findById(plane.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Plane with id " + plane.getId() + " does not exist"));;
        if(plane.getCode() != null){
            existingPlane.setCode(plane.getCode());
        }
        if(plane.getModel() != null){
            existingPlane.setModel(plane.getCode());
        }
        if(plane.getYearOfProduction() != null){
            existingPlane.setYearOfProduction(plane.getYearOfProduction());
        }

        writer.write(planeRepository, DATA_PATH);

    }

}
