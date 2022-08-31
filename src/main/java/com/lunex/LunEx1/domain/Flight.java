package com.lunex.LunEx1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Flight {

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
    public Long id;

    public String flightCode;
    public String airline;
    public String flightPlane;
    public String depAirport;
    public String desAirport;
    public String depDate;
    public String arvDate;

    public Flight(String flightCode, String airline, String flightPlane, String depAirport, String desAirport, String depDate, String arvDate) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.flightPlane = flightPlane;
        this.depAirport = depAirport;
        this.desAirport = desAirport;
        this.depDate = depDate;
        this.arvDate = arvDate;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightCode='" + flightCode + '\'' +
                ", airline='" + airline + '\'' +
                ", flightPlane='" + flightPlane + '\'' +
                ", depAirport='" + depAirport + '\'' +
                ", desAirport='" + desAirport + '\'' +
                ", depDate='" + depDate + '\'' +
                ", arvDate='" + arvDate + '\'' +
                '}';
    }
}
