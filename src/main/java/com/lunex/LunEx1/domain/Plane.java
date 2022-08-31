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
            generator = "film_sequence"
    )
    private Long id;
    private String code;
    private String model;
    private Integer yearOfProduction;


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
