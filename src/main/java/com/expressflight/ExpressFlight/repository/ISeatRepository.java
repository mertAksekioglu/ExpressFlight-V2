package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Repository
@Service
public interface ISeatRepository extends JpaRepository<Seat,Long> {


    Optional<Seat> findByCode(String seatCode);

}
