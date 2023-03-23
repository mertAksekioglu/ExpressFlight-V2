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
public class ConnectedFlight implements Serializable {

    @Id
    @SequenceGenerator(
            name = "connected_flight_sequence",
            sequenceName = "connected_flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "connected_flight_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "flight_legs")
    private Long[] flightLegs;

    @Column(name = "stop_count")
    private Integer stopCount;

    @Column(name = "price")
    private Double price;

    @Column(name = "layover_minutes")
    private Integer layoverMinutes;

    public ConnectedFlight(Long[] flightLegs, Integer stopCount, Double price, Integer layoverMinutes) {
        this.flightLegs = flightLegs;
        this.stopCount = stopCount;
        this.price = price;
        this.layoverMinutes = layoverMinutes;
    }

    @Override
    public String toString() {
        return "ConnectedFlight{" +
                "id=" + id +
                ", flightLegs=" + Arrays.toString(flightLegs) +
                ", stopCount=" + stopCount +
                ", price=" + price +
                ", layoverMinutes=" + layoverMinutes +
                '}';
    }
}
