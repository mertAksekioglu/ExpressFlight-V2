package com.lunex.LunEx1.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Plane {


    @Id
    @SequenceGenerator(
            name = "plane_sequence",
            sequenceName = "plane_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plane_sequence"
    )
    @Column(name = "id")
    private Long id; //5
    @Column(name = "code")
    private String code; // TC-SOD
    @Column(name = "model")
    private String model; // 737
    @Column(name = "year_of_production")
    private Integer yearOfProduction; // 1998


    public void Plane(String code, String model,Integer yearOfProduction) {
        this.code = code;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", model='" + model + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}
