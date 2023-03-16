package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Repository
@Service
public interface IPlaneRepository extends JpaRepository<Plane,Long> {



    Optional<Plane> findByCode(String code);
}
