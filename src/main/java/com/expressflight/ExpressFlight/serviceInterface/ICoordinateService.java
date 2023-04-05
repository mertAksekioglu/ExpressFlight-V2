package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.CoordinateDTO;

import java.util.List;

public interface ICoordinateService {


    public List<CoordinateDTO> getAllCoordinates();
    public CoordinateDTO getCoordinate(Long coordinateId);
    public CoordinateDTO addCoordinate(CoordinateDTO coordinate);
    public CoordinateDTO deleteCoordinate(Long coordinateId);
    public CoordinateDTO updateCoordinate(CoordinateDTO coordinate, Long coordinateId);


}
