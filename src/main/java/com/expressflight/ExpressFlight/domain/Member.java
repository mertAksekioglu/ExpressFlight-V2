package com.expressflight.ExpressFlight.domain;

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
public class Member {

    @Id
    @SequenceGenerator(
            name = "member_sequence",
            sequenceName = "member_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="active")
    private Integer active;

    @Column(name="passenger")
    private Long passenger;

    public Member(String email, String password, Integer active, Long passenger) {
        this.email = email;
        this.password = password;
        this.active = active;
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", passenger=" + passenger +
                '}';
    }
}
