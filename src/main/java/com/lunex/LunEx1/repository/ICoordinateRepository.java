package com.lunex.LunEx1.repository;

import com.lunex.LunEx1.domain.ConnectedFlight;
import com.lunex.LunEx1.domain.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;



@Repository
@Service
public interface ICoordinateRepository extends JpaRepository<Coordinate, Long> {


}