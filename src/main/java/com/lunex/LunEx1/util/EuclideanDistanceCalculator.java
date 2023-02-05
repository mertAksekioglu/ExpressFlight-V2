package com.lunex.LunEx1.util;

public class EuclideanDistanceCalculator implements IDistanceCalculator {

    @Override
    public Double distance(Coordinate pointA, Coordinate pointB) {
        return (Math.sqrt(Math.pow(pointA.getLatitude() - pointB.getLatitude(),2)
                + Math.pow(pointA.getLongitude() - pointB.getLongitude(),2)));
    }


}



