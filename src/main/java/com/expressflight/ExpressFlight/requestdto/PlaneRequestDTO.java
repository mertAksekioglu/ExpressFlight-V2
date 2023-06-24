package com.expressflight.ExpressFlight.requestdto;

import lombok.Data;

@Data
public class PlaneRequestDTO {
    private String model;
    private String code;
    private Integer yearOfProduction;
}
