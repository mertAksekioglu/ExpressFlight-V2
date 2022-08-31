package com.lunex.LunEx1.serviceInterface;

import com.lunex.LunEx1.domain.Plane;

import java.util.List;

public interface IPlaneService {

    public List<Plane> getAllPlanes();
    public Plane getPlane(Long planeId);
    public void addPlane(Plane plane);
    public void deletePlane(Long planeId);
    public void updatePlane(Plane plane);
}
