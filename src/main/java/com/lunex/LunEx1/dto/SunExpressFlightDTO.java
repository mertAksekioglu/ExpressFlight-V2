package com.lunex.LunEx1.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SunExpressFlightDTO {

    private String depAirport;
    private String arvAirport;
    private LocalDate depDate;
    private LocalTime depTime;
    private LocalDate arvDate;
    private LocalTime arvTime;
    private String flightCode;
    private String airline;
    private Double price;





}



