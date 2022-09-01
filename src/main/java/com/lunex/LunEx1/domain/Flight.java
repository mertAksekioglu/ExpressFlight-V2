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
    @Column(name = "id")
    public Long id;
    @Column(name = "flight_code")
    public String flightCode;
    @Column(name = "airline")
    public String airline;
    @Column(name = "flight_plane")
    public String flightPlane;
    @Column(name = "dep_airport")
    public String depAirport;
    @Column(name = "des_airport")
    public String desAirport;
    @Column(name = "dep_date")
    public String depDate;
    @Column(name = "arv_date")
    public String arvDate;
    @Column(name = "price")
    public Integer price;


    public Flight(String flightCode, String airline, String flightPlane, String depAirport,
                  String desAirport, String depDate, String arvDate, Integer price) {
        this.flightCode = flightCode;
        this.airline = airline;
        this.flightPlane = flightPlane;
        this.depAirport = depAirport;
        this.desAirport = desAirport;
        this.depDate = depDate;
        this.arvDate = arvDate;
        this.price = price;
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
                ", price=" + price +
                '}';
    }
}
