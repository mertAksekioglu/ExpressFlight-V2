package com.lunex.LunEx1.controller;

import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.service.PlaneService;
import com.lunex.LunEx1.serviceInterface.IPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/plane")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaneController {

    @Autowired
    private IPlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public List<Plane> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @GetMapping(value = "/get-id")
    public Plane getPlane(@RequestParam(value = "id") Long planeId) {
        return planeService.getPlane(planeId);
    }

    @PostMapping(value = "/add-plane")
    public void addPlane(@RequestBody Plane plane) {
        planeService.addPlane(plane);
    }

    @DeleteMapping(value = "/delete-id")
    public void deletePlane(@RequestParam(value = "id") Long planeId) {
        planeService.deletePlane(planeId);
    }

    @PutMapping(value = "/update-plane")
    public void updatePlane(@RequestBody Plane plane) {
        planeService.updatePlane(plane);
    }



}
