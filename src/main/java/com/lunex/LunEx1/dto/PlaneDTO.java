package com.lunex.LunEx1.dto;

import lombok.Data;

@Data
public class PlaneDTO {
    private Long id;
    private String model;
    private String code;
    private Integer yearOfProduction;
}
