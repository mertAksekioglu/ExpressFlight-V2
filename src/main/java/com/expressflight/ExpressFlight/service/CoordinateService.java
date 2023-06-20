package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Coordinate;
import com.expressflight.ExpressFlight.dto.CoordinateDTO;
import com.expressflight.ExpressFlight.repository.ICoordinateRepository;
import com.expressflight.ExpressFlight.serviceInterface.ICoordinateService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoordinateService implements ICoordinateService {

    private ICoordinateRepository coordinateRepository;

    private ModelMapper modelMapper;

    public CoordinateService(ICoordinateRepository coordinateRepository, ModelMapper modelMapper) {
        this.coordinateRepository = coordinateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CoordinateDTO> getAllCoordinates() {
        List<Coordinate> coordinates = coordinateRepository.findAll();
        List<CoordinateDTO> coordinateDtos = new ArrayList<>();
        for (Coordinate coordinate : coordinates) {
            CoordinateDTO coordinateDto = modelMapper.map(coordinate, CoordinateDTO.class);
            coordinateDtos.add(coordinateDto);
        }
        return coordinateDtos;
    }

    @Override
    public CoordinateDTO getCoordinate(Long coordinateId) {
        Optional<Coordinate> coordinate = coordinateRepository.findById(coordinateId);
        if(!coordinate.isPresent()) {
            throw new IllegalStateException("Coordinate with the id " + coordinateId + " does not exist");
        }
        CoordinateDTO returningCoordinateDto = modelMapper.map(coordinate.get(), CoordinateDTO.class);
        return returningCoordinateDto;
    }

    @Override
    public CoordinateDTO addCoordinate(CoordinateDTO coordinateDto) {
        Coordinate coordinate = modelMapper.map(coordinateDto,Coordinate.class);
        checkAlreadyExistsByLongitudeAndLatitude(coordinateDto);
        coordinateRepository.save(coordinate);
        CoordinateDTO returningCoordinateDto = modelMapper.map(coordinate, CoordinateDTO.class);
        return returningCoordinateDto;
    }

    @Override
    public CoordinateDTO deleteCoordinate(Long coordinateId) {
        Optional<Coordinate> coordinate = coordinateRepository.findById(coordinateId);
        if(!coordinate.isPresent()) {
            throw new IllegalStateException("Coordinate with the id " + coordinateId + " does not exist");
        }
        coordinateRepository.deleteById(coordinateId);
        CoordinateDTO returningCoordinateDto = modelMapper.map(coordinate.get(), CoordinateDTO.class);
        return returningCoordinateDto;
    }

    @Override
    @Transactional
    public CoordinateDTO updateCoordinate(CoordinateDTO coordinateDto, Long coordinateId) {
        Coordinate coordinate = modelMapper.map(coordinateDto,Coordinate.class);
        Optional<Coordinate> existingCoordinate = coordinateRepository.findById(coordinateId);
        if(!existingCoordinate.isPresent()) {
            throw new IllegalStateException("Coordinate with the id " + existingCoordinate.get().getId() + " does not exist.");
        }
        if(coordinate.getLatitude() != null){
            existingCoordinate.get().setLatitude(coordinateDto.getLatitude());
        }
        if(coordinate.getLongitude() != null){
            existingCoordinate.get().setLongitude(coordinateDto.getLongitude());
        }
        CoordinateDTO returningCoordinateDto = modelMapper.map(existingCoordinate.get(), CoordinateDTO.class);
        return returningCoordinateDto;
    }


    private boolean checkAlreadyExistsByLongitudeAndLatitude(CoordinateDTO coordinateDto) {
        boolean exists = coordinateRepository.existsByLongitudeAndLatitude(coordinateDto.getLongitude(),coordinateDto.getLatitude());
        if(exists) {
            throw new IllegalStateException("Coordinate with the longitude " + coordinateDto.getLongitude() +
                    " and latitude " + coordinateDto.getLatitude()  + " already exists.");
        }
        return exists;
    }

}
