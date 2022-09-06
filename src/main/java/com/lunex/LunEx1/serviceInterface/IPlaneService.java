package com.lunex.LunEx1.serviceInterface;

import com.lunex.LunEx1.domain.Plane;
import com.lunex.LunEx1.dto.PlaneDTO;

import java.util.List;

public interface IPlaneService {

    public List<PlaneDTO> getAllPlanes();
    public PlaneDTO getPlane(Long planeId);
    public PlaneDTO getPlaneByCode(String planeCode);
    public void addPlane(PlaneDTO plane);
    public void deletePlane(Long planeId);
    public void updatePlane(PlaneDTO plane);
}
