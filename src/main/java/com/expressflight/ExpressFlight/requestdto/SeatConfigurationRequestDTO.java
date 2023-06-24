package com.expressflight.ExpressFlight.requestdto;

import com.expressflight.ExpressFlight.domain.Seat;
import lombok.Data;

@Data
public class SeatConfigurationRequestDTO {

    private String configPlane;
    private String configName;
    private Boolean isConfigured;
    private Seat[][] seatMap;




}
