package com.expressflight.ExpressFlight.dto;

import com.expressflight.ExpressFlight.domain.Seat;
import lombok.Data;

import javax.persistence.Column;

@Data
public class SeatConfigurationDTO {

    private String configPlane;
    private String configName;
    private Boolean isConfigured;
    private SeatDTO[][] seatMap;

}
