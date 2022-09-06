package com.lunex.LunEx1.dto;

import lombok.Data;

@Data
public class FlightSearchRequestDTO {
    private String depAirport;
    private String desAirport;
    private String depDate;
}
