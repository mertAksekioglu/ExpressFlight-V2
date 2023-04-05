package com.expressflight.ExpressFlight.util.seatMapper;


import org.springframework.stereotype.Service;

@Service
public class SeatConfigurationFactory {
    public ISeatMapper createSeatConfiguration(String configType) {
        switch (configType) {
            case "SunExpress7378HC":
                return new SunExpress7378HC();
            case "PegasusA320251N":
                return new PegasusA320251N();
            // TODO Add THY Config
            default:
                throw new IllegalArgumentException("Invalid seat configuration type: " + configType);
        }
    }
}