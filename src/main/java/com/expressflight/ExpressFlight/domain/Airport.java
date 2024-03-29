package com.expressflight.ExpressFlight.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Airport implements Serializable {

    @Id
    @SequenceGenerator(
            name = "airport_sequence",
            sequenceName = "airport_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "airport_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code_IATA")
    private String codeIATA;

    @Column(name = "code_ICAO")
    private String codeICAO;

    @Column(name = "location")
    private Long location;

    @Column(name ="city")
    private String city;

    @Column(name ="country")
    private String country;

    @Column(name = "terminal_count")
    private Integer terminalCount;

    @Column(name = "runway_count")
    private Integer runwayCount;

    public Airport(String name, String codeIATA, String codeICAO,
                   Long location, String city, String country, Integer terminalCount, Integer runwayCount) {
        this.name = name;
        this.codeIATA = codeIATA;
        this.codeICAO = codeICAO;
        this.location = location;
        this.city = city;
        this.country = country;
        this.terminalCount = terminalCount;
        this.runwayCount = runwayCount;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codeIATA='" + codeIATA + '\'' +
                ", codeICAO='" + codeICAO + '\'' +
                ", location=" + location +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", terminalCount=" + terminalCount +
                ", runwayCount=" + runwayCount +
                '}';
    }

}
