package com.expressflight.ExpressFlight.dto;

import com.expressflight.ExpressFlight.domain.Flight;
import lombok.Data;

@Data
public class ConnectedFlightDTO {

    private FlightDTO[] flightLegs;
    private Integer stopCount;
    private Double price;
    private Integer layoverMinutes;

}
