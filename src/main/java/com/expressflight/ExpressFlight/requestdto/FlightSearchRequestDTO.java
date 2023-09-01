package com.expressflight.ExpressFlight.requestdto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FlightSearchRequestDTO {
    private String depAirport;
    private String desAirport;
    private LocalDate depDate;
}
