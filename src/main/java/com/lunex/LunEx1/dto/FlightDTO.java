package com.lunex.LunEx1.dto;

import com.lunex.LunEx1.domain.FlightSegment;
import com.lunex.LunEx1.domain.Plane;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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





}
