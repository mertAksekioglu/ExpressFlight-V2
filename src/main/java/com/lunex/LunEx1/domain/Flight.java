package com.lunex.LunEx1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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

    @Column(name = "departure_airport")
    private Long depAirport;

    @Column(name = "arrival_airport")
    private Long arvAirport;
    
    @Column(name = "departure_date_time")
    private LocalDateTime depDateTime;

    @Column(name = "arrival_date_time")
    private LocalDateTime arvDateTime;

    @Column(name = "flight_code")
    private String flightCode;

    @Column(name = "airline")
    private String airline;

    @Column(name = "price")
    private Double price;

    public Flight(Long depAirport, Long arvAirport, LocalDateTime depDateTime,
                  LocalDateTime arvDateTime, String flightCode, String airline, Double price) {
        this.depAirport = depAirport;
        this.arvAirport = arvAirport;
        this.depDateTime = depDateTime;
        this.arvDateTime = arvDateTime;
        this.flightCode = flightCode;
        this.airline = airline;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", depAirport=" + depAirport +
                ", arvAirport=" + arvAirport +
                ", depDateTime=" + depDateTime +
                ", arvDateTime=" + arvDateTime +
                ", flightCode='" + flightCode + '\'' +
                ", airline='" + airline + '\'' +
                ", price=" + price +
                '}';
    }
}
