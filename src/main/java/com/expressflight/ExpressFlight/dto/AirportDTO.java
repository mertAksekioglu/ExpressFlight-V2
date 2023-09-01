package com.expressflight.ExpressFlight.dto;

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
