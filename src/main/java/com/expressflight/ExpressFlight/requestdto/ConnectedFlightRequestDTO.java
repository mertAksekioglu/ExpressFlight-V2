package com.expressflight.ExpressFlight.requestdto;

import lombok.Data;

@Data
public class ConnectedFlightRequestDTO {


    private Long[] flightLegs;
    private Integer stopCount;
    private Double price;
    private Integer layoverMinutes;
}
