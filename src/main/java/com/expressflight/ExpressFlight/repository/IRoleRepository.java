package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Plane;
import com.expressflight.ExpressFlight.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
@Service
public interface IRoleRepository extends JpaRepository<Role,Long> {
}
