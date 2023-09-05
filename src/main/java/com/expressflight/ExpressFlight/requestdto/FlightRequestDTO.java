package com.expressflight.ExpressFlight.requestdto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FlightRequestDTO {

    private String airline;
    private String flightCode;
    private Double price;
    private Long depAirport;
    private Long arvAirport;
    private LocalDateTime depDateTime;
    private LocalDateTime arvDateTime;
    private Boolean isInternational;
    private String seatConfigName;
    private Long seatConfig;





}
