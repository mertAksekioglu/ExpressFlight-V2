package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.SeatConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
@Service
public interface ISeatConfigurationRepository extends JpaRepository<SeatConfiguration, Long> {

    Optional<SeatConfiguration> findByConfigName(String configName);
    List<SeatConfiguration> findByIsConfigured(Boolean isConfigured);
}
