package com.lunex.LunEx1.util;

import com.lunex.LunEx1.domain.Coordinate;

public class EuclideanDistanceCalculator implements IDistanceCalculator {

    @Override
    public Double distance(Coordinate pointA, Coordinate pointB) {
        return (Math.sqrt(Math.pow(pointA.getLatitude() - pointB.getLatitude(),2)
                + Math.pow(pointA.getLongitude() - pointB.getLongitude(),2)));
    }


}




