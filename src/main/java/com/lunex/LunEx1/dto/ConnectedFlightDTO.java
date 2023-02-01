package com.lunex.LunEx1.dto;

import com.lunex.LunEx1.domain.Flight;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ConnectedFlightDTO {


    private Flight[] flightLegs;
    private int stopCount;
    private double price;
    private LocalTime layoverDuration;
}
