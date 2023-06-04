package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface IPassengerRepository extends JpaRepository<Passenger, Long> {
}
