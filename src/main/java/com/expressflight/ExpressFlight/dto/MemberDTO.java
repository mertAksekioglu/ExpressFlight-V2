package com.expressflight.ExpressFlight.dto;

import lombok.Data;



@Data
public class MemberDTO {

    private String email;
    private String password;
    private Integer active;
    private PassengerDTO passenger;
}
