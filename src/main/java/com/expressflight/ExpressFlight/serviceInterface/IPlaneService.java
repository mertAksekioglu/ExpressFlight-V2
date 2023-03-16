package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.PlaneDTO;

import java.util.List;

public interface IPlaneService {

    public List<PlaneDTO> getAllPlanes();
    public PlaneDTO getPlane(Long planeId);
    public PlaneDTO getPlaneByCode(String planeCode);
    public PlaneDTO addPlane(PlaneDTO plane);
    public PlaneDTO deletePlane(Long planeId);
    public PlaneDTO updatePlane(PlaneDTO plane, Long planeId);
}
