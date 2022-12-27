package com.lunex.LunEx1.repository;

import com.lunex.LunEx1.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
@Service
public interface IAirportRepository extends JpaRepository<Airport, Long> {


    Optional<Airport> findByCode(String code);
}
