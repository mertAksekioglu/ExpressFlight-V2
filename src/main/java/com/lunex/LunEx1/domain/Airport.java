package com.lunex.LunEx1.domain;

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
    @Column(name = "code")
    private String code;
    @Column(name = "terminal_count")
    private Integer terminalCount;
    @Column(name = "runway_count")
    private Integer runwayCount;

    public Airport(String code, Integer terminalCount, Integer runwayCount) {
        this.code = code;
        this.terminalCount = terminalCount;
        this.runwayCount = runwayCount;
    }


    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", terminalCount=" + terminalCount +
                ", runwayCount=" + runwayCount +
                '}';
    }



}
