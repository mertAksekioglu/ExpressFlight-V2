package com.lunex.LunEx1.dto;

import lombok.Data;

@Data
public class AirportDTO {
    private Long id;
    private String code;
    private Integer terminalCount;
    private Integer runwayCount;
}
