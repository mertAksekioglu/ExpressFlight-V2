package com.expressflight.ExpressFlight.domain;

import com.expressflight.ExpressFlight.enums.SeatStatus;
import com.expressflight.ExpressFlight.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Seat {



    @Id
    @SequenceGenerator(
            name = "seat_sequence",
            sequenceName = "seat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seat_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "type")
    private SeatType type;

    @Column(name = "status")
    private SeatStatus status;

    public Seat(String code, SeatType type, SeatStatus status) {
        this.code = code;
        this.type = type;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }

}
