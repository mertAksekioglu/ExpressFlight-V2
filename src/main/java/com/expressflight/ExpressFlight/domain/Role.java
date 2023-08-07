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
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="role")
    private String role;

    public Role(String email, String role) {
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
