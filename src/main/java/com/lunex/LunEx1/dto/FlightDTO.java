package com.lunex.LunEx1.dto;

import com.lunex.LunEx1.domain.FlightSegment;
import com.lunex.LunEx1.domain.Plane;
import lombok.Data;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
public class FlightDTO {

    private String depAirport;
    private String arvAirport;
    private String depDate;
    private String depTime;
    private String arvDate;
    private String arvTime;
    private String flightCode;
    private String airline;
    private Integer price;


}
