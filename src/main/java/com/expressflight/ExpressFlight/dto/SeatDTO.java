package com.expressflight.ExpressFlight.dto;


import com.expressflight.ExpressFlight.enums.SeatStatus;
import com.expressflight.ExpressFlight.enums.SeatType;
import lombok.Data;

@Data
public class SeatDTO {

    private String code;
    private SeatType type;
    private SeatStatus status;

}
