package com.expressflight.ExpressFlight.dto;

import lombok.Data;

@Data
public class SeatConfigurationDTO {

    private String configPlane;
    private String configName;
    private Boolean isConfigured;
    private SeatDTO[][] seatMap;

}
