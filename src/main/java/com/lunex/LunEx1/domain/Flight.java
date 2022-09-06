package com.lunex.LunEx1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Flight implements Serializable {

    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_sequence"
    )
    @Column(name = "id")
    private Long id;
   // @ElementCollection(targetClass=FlightSegment.class)
    @Column(name = "flight_segments")
    private FlightSegment[] flightSegments;
    @Column(name = "is_connected")
    private Boolean isConnected;
    @Column(name = "price")
    private Integer price;


    public Flight(FlightSegment[] flightSegments, Boolean isConnected, Integer price) {
        this.flightSegments = flightSegments;
        this.isConnected = isConnected;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightSegments=" + Arrays.toString(flightSegments) +
                ", isConnected=" + isConnected +
                ", price=" + price +
                '}';
    }
}
