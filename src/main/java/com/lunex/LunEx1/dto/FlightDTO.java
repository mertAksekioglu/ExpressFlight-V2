package com.lunex.LunEx1.dto;

import com.lunex.LunEx1.domain.FlightSegment;
import com.lunex.LunEx1.domain.Plane;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
public class FlightDTO {
    private Long id;
    private List<FlightSegment> flightSegments;
    private Boolean isConnected;
    private Integer price;


}
