package com.lunex.LunEx1.dto;

import com.lunex.LunEx1.domain.Coordinate;
import lombok.Data;

@Data
public class AirportDTO {
    private String name;
    private String codeIATA;
    private String codeICAO;
    private Coordinate location;
    private Integer terminalCount;
    private Integer runwayCount;
}
