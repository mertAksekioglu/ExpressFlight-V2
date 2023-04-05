package com.expressflight.ExpressFlight.dto;

import com.expressflight.ExpressFlight.enums.Gender;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class PassengerDTO {


    private String name;
    private String surname;
    private String email;
    private String phone;
    private Gender gender;
    private Integer age;
    private LocalDate dateOfBirth;


}
