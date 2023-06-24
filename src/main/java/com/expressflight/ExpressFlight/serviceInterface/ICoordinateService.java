package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.CoordinateDTO;
import com.expressflight.ExpressFlight.requestdto.CoordinateRequestDTO;

import java.util.List;

public interface ICoordinateService {

    public List<CoordinateDTO> getAllCoordinates();

    public CoordinateDTO getCoordinate(Long coordinateId);

    public CoordinateDTO addCoordinate(CoordinateRequestDTO coordinateRequestDto);

    public CoordinateDTO deleteCoordinate(Long coordinateId);

    public CoordinateDTO updateCoordinate(CoordinateRequestDTO coordinateRequestDto, Long coordinateId);

}
