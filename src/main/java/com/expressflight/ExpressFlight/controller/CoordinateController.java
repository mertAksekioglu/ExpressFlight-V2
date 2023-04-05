package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.CoordinateDTO;
import com.expressflight.ExpressFlight.service.CoordinateService;
import com.expressflight.ExpressFlight.serviceInterface.ICoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/coordinate")
@CrossOrigin(origins = "*")
public class CoordinateController {

    @Autowired
    private ICoordinateService coordinateService;

    @Autowired
    public CoordinateController(CoordinateService coordinateService) {
        this.coordinateService = coordinateService;
    }

    @GetMapping
    public List<CoordinateDTO> getAllCoordinates() {
        return coordinateService.getAllCoordinates();
    }

    @GetMapping(value = "/get-id")
    public CoordinateDTO getCoordinate(@RequestParam(value = "id") Long coordinateId) {
        return coordinateService.getCoordinate(coordinateId);
    }

    @PostMapping(value = "/add-coordinate")
    public CoordinateDTO addCoordinate(@RequestBody CoordinateDTO coordinateDto) {
        return coordinateService.addCoordinate(coordinateDto);
    }

    @DeleteMapping(value = "/delete-id")
    public CoordinateDTO deleteCoordinate(@RequestParam(value = "id") Long coordinateId) {
        return coordinateService.deleteCoordinate(coordinateId);
    }

    @PutMapping(value = "/update-coordinate")
    public CoordinateDTO updateCoordinate(@RequestBody CoordinateDTO coordinateDto, @RequestParam(value = "id") Long coordinateId) {
        return coordinateService.updateCoordinate(coordinateDto, coordinateId);
    }



}
