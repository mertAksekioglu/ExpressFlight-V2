package com.lunex.LunEx1.repository;

import com.lunex.LunEx1.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
@Service
public interface IPlaneRepository extends JpaRepository<Plane,Long> {

}
