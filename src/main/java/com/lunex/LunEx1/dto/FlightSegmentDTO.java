package com.lunex.LunEx1.dto;

import lombok.Data;

@Data
public class FlightSegmentDTO {



    private Long planeId;
    private String depAirport;
    private String arvAirport;
    private String depDate;
    private String depTime;
    private String arvDate;
    private String arvTime;
    private String flightCode;
    private String airline;
}
