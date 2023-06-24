package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.PlaneDTO;
import com.expressflight.ExpressFlight.requestdto.PlaneRequestDTO;

import java.util.List;

public interface IPlaneService {

    public List<PlaneDTO> getAllPlanes();

    public PlaneDTO getPlane(Long planeId);

    public PlaneDTO getPlaneByCode(String planeCode);

    public PlaneDTO addPlane(PlaneRequestDTO planeRequestDto);

    public PlaneDTO deletePlane(Long planeId);

    public PlaneDTO updatePlane(PlaneRequestDTO planeRequestDto, Long planeId);
}
