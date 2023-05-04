package com.expressflight.ExpressFlight.util.seatMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatMapFactory {


    @Autowired
    SunExpress7378HC sunExpress7378HC;
    @Autowired
    PegasusA320251N pegasusA320251N;


    public ISeatMapper createSeatMap(String mapType) {
        switch (mapType) {
            case "SunExpress7378HC":
                return sunExpress7378HC;
            case "PegasusA320251N":
                return pegasusA320251N;
            // TODO Add THY Config
            default:
                throw new IllegalArgumentException("Invalid seat map type: " + mapType);
        }
    }
}