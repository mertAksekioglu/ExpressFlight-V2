package com.expressflight.ExpressFlight.domain;

import com.expressflight.ExpressFlight.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Passenger implements Serializable {

    @Id
    @SequenceGenerator(
     name= "passenger_sequence",
     sequenceName = "passenger_sequence",
     allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "passenger_sequence"
    )
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "emal")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "age")
    private Integer age;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    public Passenger(String name, String surname, String email,
                     String phone, Gender gender, Integer age, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
