package com.expressflight.ExpressFlight.requestdto;

import lombok.Data;

@Data
public class MemberRequestDTO {

    private String email;
    private String password;
    private Integer active;
    private Long passenger;
}
