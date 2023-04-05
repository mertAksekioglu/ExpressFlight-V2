package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface IFlightRepository extends JpaRepository<Flight,Long> {

    List<Flight> findByFlightCode(String flightCode);



}

