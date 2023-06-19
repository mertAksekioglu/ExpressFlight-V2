package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.PlaneDTO;
import com.expressflight.ExpressFlight.service.PlaneService;
import com.expressflight.ExpressFlight.serviceInterface.IPlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/plane")
@CrossOrigin(origins = "*")
public class PlaneController {

    private IPlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public List<PlaneDTO> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @GetMapping(value = "/get-id")
    public PlaneDTO getPlane(@RequestParam(value = "id") Long planeId) {
        return planeService.getPlane(planeId);
    }

    @GetMapping(value = "/get-code")
    public PlaneDTO getPlane(@RequestParam(value = "code") String planeCode) {
        return planeService.getPlaneByCode(planeCode);
    }

    @PostMapping(value = "/add-plane")
    public PlaneDTO addPlane(@RequestBody PlaneDTO planeDto) {
        return planeService.addPlane(planeDto);
    }

    @DeleteMapping(value = "/delete-id")
    public PlaneDTO deletePlane(@RequestParam(value = "id") Long planeId) {
        return planeService.deletePlane(planeId);
    }

    @PutMapping(value = "/update-plane")
    public PlaneDTO updatePlane(@RequestBody PlaneDTO planeDto, @RequestParam(value = "id") Long planeId) {
        return planeService.updatePlane(planeDto, planeId);
    }

}
