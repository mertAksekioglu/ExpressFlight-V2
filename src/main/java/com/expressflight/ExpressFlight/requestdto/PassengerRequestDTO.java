package com.expressflight.ExpressFlight.requestdto;

import com.expressflight.ExpressFlight.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PassengerRequestDTO {


    private String name;
    private String surname;
    private String email;
    private String phone;
    private Gender gender;
    private Integer age;
    private LocalDate dateOfBirth;


}
