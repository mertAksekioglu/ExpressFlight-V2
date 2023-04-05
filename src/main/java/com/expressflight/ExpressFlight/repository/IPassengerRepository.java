package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPassengerRepository extends JpaRepository<Passenger, Long> {
}
