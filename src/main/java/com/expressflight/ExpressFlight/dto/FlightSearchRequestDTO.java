package com.expressflight.ExpressFlight.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightSearchRequestDTO {
    private Long depAirport;
    private Long desAirport;
    private LocalDateTime depDateTime;
}
