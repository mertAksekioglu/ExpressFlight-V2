package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Coordinate;
import com.expressflight.ExpressFlight.dto.CoordinateDTO;
import com.expressflight.ExpressFlight.repository.ICoordinateRepository;
import com.expressflight.ExpressFlight.requestdto.CoordinateRequestDTO;
import com.expressflight.ExpressFlight.serviceInterface.ICoordinateService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return coordinates.stream()
                .map(coordinate -> convertToDTO(coordinate))
                .collect(Collectors.toList());
    }

    @Override
    public CoordinateDTO getCoordinate(Long coordinateId) {
        Optional<Coordinate> coordinate = coordinateRepository.findById(coordinateId);
        if(!coordinate.isPresent()) {
            throw new IllegalStateException("Coordinate with the id " + coordinateId + " does not exist");
        }
        return convertToDTO(coordinate.get());
    }

    @Override
    public CoordinateDTO addCoordinate(CoordinateRequestDTO coordinateRequestDto) {
        Coordinate coordinate = convertToEntity(coordinateRequestDto);
        checkAlreadyExistsByLongitudeAndLatitude(coordinateRequestDto);
        coordinateRepository.save(coordinate);
        return convertToDTO(coordinate);
    }

    @Override
    public CoordinateDTO deleteCoordinate(Long coordinateId) {
        Optional<Coordinate> coordinate = coordinateRepository.findById(coordinateId);
        if(!coordinate.isPresent()) {
            throw new IllegalStateException("Coordinate with the id " + coordinateId + " does not exist");
        }
        coordinateRepository.deleteById(coordinateId);
        return convertToDTO(coordinate.get());
    }

    @Override
    @Transactional
    public CoordinateDTO updateCoordinate(CoordinateRequestDTO coordinateRequestDto, Long coordinateId) {
        Coordinate coordinate = convertToEntity(coordinateRequestDto);
        Optional<Coordinate> existingCoordinate = coordinateRepository.findById(coordinateId);
        if(!existingCoordinate.isPresent()) {
            throw new IllegalStateException("Coordinate with the id " + existingCoordinate.get().getId() + " does not exist.");
        }
        if(coordinate.getLatitude() != null){
            existingCoordinate.get().setLatitude(coordinate.getLatitude());
        }
        if(coordinate.getLongitude() != null){
            existingCoordinate.get().setLongitude(coordinate.getLongitude());
        }
        return convertToDTO(existingCoordinate.get());
    }


    private boolean checkAlreadyExistsByLongitudeAndLatitude(CoordinateRequestDTO coordinateRequestDto) {
        boolean exists = coordinateRepository.existsByLongitudeAndLatitude(coordinateRequestDto.getLongitude(),coordinateRequestDto.getLatitude());
        if(exists) {
            throw new IllegalStateException("Coordinate with the longitude " + coordinateRequestDto.getLongitude() +
                    " and latitude " + coordinateRequestDto.getLatitude()  + " already exists.");
        }
        return exists;
    }

    private CoordinateDTO convertToDTO(Coordinate coordinate) {
        CoordinateDTO coordinateDto = modelMapper.map(coordinate, CoordinateDTO.class);
        return coordinateDto;
    }

    private Coordinate convertToEntity(CoordinateRequestDTO coordinateRequestDto) {
        Coordinate coordinate = modelMapper.map(coordinateRequestDto, Coordinate.class);
        return coordinate;
    }

}
