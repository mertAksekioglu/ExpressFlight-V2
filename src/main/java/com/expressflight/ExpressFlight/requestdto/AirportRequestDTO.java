package com.expressflight.ExpressFlight.requestdto;

import lombok.Data;

@Data
public class AirportRequestDTO {
    private String name;
    private String codeIATA;
    private String codeICAO;
    private Long location;
    private Integer terminalCount;
    private Integer runwayCount;
}
