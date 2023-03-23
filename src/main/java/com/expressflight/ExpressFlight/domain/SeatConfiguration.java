package com.expressflight.ExpressFlight.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class SeatConfiguration implements Serializable {


    @Id
    @SequenceGenerator(
            name = "seat_configuration_sequence",
            sequenceName = "seat_configuration_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "seat_configuration_sequence"

    )
    @Column(name = "id")
    private Long id;

    @Column(name = "config_plane")
    private String configPlane;

    @Column(name = "config_name")
    private String configName;

    @Column(name = "seat_configuration")
    private Seat[][] seatConfiguration;

    public SeatConfiguration(String configPlane, String configName, Seat[][] seatConfiguration) {
        this.configPlane = configPlane;
        this.configName = configName;
        this.seatConfiguration = seatConfiguration;
    }

    @Override
    public String toString() {
        return "SeatConfiguration{" +
                "id=" + id +
                ", configPlane='" + configPlane + '\'' +
                ", configName='" + configName + '\'' +
                ", seatConfiguration=" + Arrays.toString(seatConfiguration) +
                '}';
    }
}
