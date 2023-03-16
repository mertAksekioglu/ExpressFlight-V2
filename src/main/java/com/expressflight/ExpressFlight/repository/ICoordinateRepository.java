package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;



@Repository
@Service
public interface ICoordinateRepository extends JpaRepository<Coordinate, Long> {


}