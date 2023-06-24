package com.expressflight.ExpressFlight.requestdto;


import com.expressflight.ExpressFlight.enums.SeatStatus;
import com.expressflight.ExpressFlight.enums.SeatType;
import lombok.Data;

@Data
public class SeatRequestDTO {

    private Long id;
    private String code;
    private SeatType type;
    private SeatStatus status;
}
