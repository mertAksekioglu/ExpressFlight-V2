package com.expressflight.ExpressFlight.domain;

import com.expressflight.ExpressFlight.enums.TicketType;
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
public class Ticket implements Serializable {

    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    @Column(name="id")
    private Long id;

    @Column(name="flight")
    private Long flight;

    @Column(name="passenger")
    private Long passenger;

    @Column(name="seat")
    private Long seat;

    @Column(name="ticket_type")
    private TicketType ticketType;

    public Ticket(Long flight, Long passenger, Long seat, TicketType ticketType) {
        this.flight = flight;
        this.passenger = passenger;
        this.seat = seat;
        this.ticketType = ticketType;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flight=" + flight +
                ", passenger=" + passenger +
                ", seat=" + seat +
                ", ticketType=" + ticketType +
                '}';
    }
}
