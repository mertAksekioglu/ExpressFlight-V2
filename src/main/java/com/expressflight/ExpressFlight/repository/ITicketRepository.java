package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Seat;
import com.expressflight.ExpressFlight.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
@Service
public interface ITicketRepository extends JpaRepository<Ticket, Long> {
}
