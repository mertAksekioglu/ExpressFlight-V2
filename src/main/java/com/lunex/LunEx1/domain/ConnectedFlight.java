package com.lunex.LunEx1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
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
    private Flight[] flightLegs;

    @Column(name = "stop_count")
    private int stopCount;

    @Column(name = "price")
    private double price;

    @Column(name = "layover_duration")
    private LocalTime layoverDuration;

    public ConnectedFlight(Flight[] flightLegs, int stopCount, double price, LocalTime layoverDuration) {
        this.flightLegs = flightLegs;
        this.stopCount = stopCount;
        this.price = price;
        this.layoverDuration = layoverDuration;
    }

    @Override
    public String toString() {
        return "ConnectedFlight{" +
                "id=" + id +
                ", flightLegs=" + Arrays.toString(flightLegs) +
                ", stopCount=" + stopCount +
                ", price=" + price +
                ", layoverDuration=" + layoverDuration +
                '}';
    }
}
