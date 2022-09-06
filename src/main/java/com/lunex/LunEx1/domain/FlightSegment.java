package com.lunex.LunEx1.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class FlightSegment implements Serializable {

    @Id
    @SequenceGenerator(
            name = "flight_segment_sequence",
            sequenceName = "flight_segment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_segment_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "dep_airport")
    private String depAirport;

    @Column(name = "des_airport")
    private String desAirport;

    @Column(name = "dep_date")
    private String depDate;

    @Column(name = "dep_time")
    private String depTime;

    @Column(name = "arv_date")
    private String arvDate;

    @Column(name = "arv_time")
    private String arvTime;


    @Column(name = "flight_code")
    private String flightCode;

    @Column(name = "airline")
    private String airline;

    @Column(name = "plane_id")
    private Long planeId;


    public FlightSegment(String depAirport, String desAirport, String depDate, String depTime,
                         String arvDate, String arvTime, String flightCode, String airline, Long planeId) {
        this.depAirport = depAirport;
        this.desAirport = desAirport;
        this.depDate = depDate;
        this.depTime = depTime;
        this.arvDate = arvDate;
        this.arvTime = arvTime;

        this.flightCode = flightCode;
        this.airline = airline;
        this.planeId = planeId;
    }


    @Override
    public String toString() {
        return "FlightSegment{" +
                "id=" + id +
                ", depAirport='" + depAirport + '\'' +
                ", desAirport='" + desAirport + '\'' +
                ", depDate='" + depDate + '\'' +
                ", depTime='" + depTime + '\'' +
                ", arvDate='" + arvDate + '\'' +
                ", arvTime='" + arvTime + '\'' +
                ", flightCode='" + flightCode + '\'' +
                ", airline='" + airline + '\'' +
                ", planeId=" + planeId +
                '}';
    }
}
