package com.expressflight.ExpressFlight.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Column(name = "is_configured")
    private Boolean isConfigured;

    @Column(name = "seat_map")
    private Seat[][] seatMap;



    public SeatConfiguration(String configPlane, String configName, Boolean isConfigured, Seat[][] seatMap) {
        this.configPlane = configPlane;
        this.configName = configName;
        this.isConfigured = isConfigured;
        this.seatMap = seatMap;
    }


    public SeatConfiguration(Long id, String configPlane, String configName, Boolean isConfigured) {
        this.id = id;
        this.configPlane = configPlane;
        this.configName = configName;
        this.isConfigured = isConfigured;

    }

    public SeatConfiguration(String configPlane, String configName, Boolean isConfigured) {
        this.configPlane = configPlane;
        this.configName = configName;
        this.isConfigured = isConfigured;
    }


    @Override
    public String toString() {
        return "SeatConfiguration{" +
                "id=" + id +
                ", configPlane='" + configPlane + '\'' +
                ", configName='" + configName + '\'' +
                ", isConfigured=" + isConfigured +
                ", seatMap=" + Arrays.toString(seatMap) +
                '}';
    }
}
