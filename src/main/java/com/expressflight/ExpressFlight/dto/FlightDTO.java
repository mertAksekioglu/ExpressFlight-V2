package com.expressflight.ExpressFlight.dto;

import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FlightDTO {

    private String airline;
    private String flightCode;
    private Double price;
    private Long depAirport;
    private Long arvAirport;
    private LocalDateTime depDateTime;
    private LocalDateTime arvDateTime;
    private SeatConfiguration seatConfig;





}
