package com.lunex.LunEx1.dto;

import lombok.Data;

@Data
public class FlightSegmentDTO {


    private String flightCode;
    private String airline;
    private Long planeId;
    private String depAirport;
    private String desAirport;
    private String depDate;
    private String depTime;
    private String arvDate;
    private String arvTime;
}
