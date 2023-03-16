package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
@Service
public interface IAirportRepository extends JpaRepository<Airport, Long> {


    Optional<Airport> findByCodeIATA(String codeIATA);
}
