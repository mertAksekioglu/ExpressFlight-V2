package com.expressflight.ExpressFlight.dto;

import com.expressflight.ExpressFlight.domain.Coordinate;
import lombok.Data;

@Data
public class AirportDTO {

    private String name;
    private String codeIATA;
    private String codeICAO;
    private CoordinateDTO location;
    private Integer terminalCount;
    private Integer runwayCount;

}
