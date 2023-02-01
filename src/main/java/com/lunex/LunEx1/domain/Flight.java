package com.lunex.LunEx1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


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
    @Column(name = "departure_airport")
    private String depAirport;

    @Column(name = "arrival_airport")
    private String arvAirport;

    @Column(name = "departure_date")
    private String depDate;

    @Column(name = "departure_time")
    private String depTime;

    @Column(name = "arrival_date")
    private String arvDate;

    @Column(name = "arrival_time")
    private String arvTime;

    @Column(name = "flight_code")
    private String flightCode;

    @Column(name = "airline")
    private String airline;

    @Column(name = "price")
    private Integer price;


    public Flight(String depAirport, String arvAirport, String depDate, String depTime, String arvDate, String arvTime, String flightCode, String airline, Boolean isConnected, Integer price) {
        this.depAirport = depAirport;
        this.arvAirport = arvAirport;
        this.depDate = depDate;
        this.depTime = depTime;
        this.arvDate = arvDate;
        this.arvTime = arvTime;
        this.flightCode = flightCode;
        this.airline = airline;
        this.price = price;
    }

}
