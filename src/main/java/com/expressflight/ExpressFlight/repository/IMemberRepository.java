package com.expressflight.ExpressFlight.repository;

import com.expressflight.ExpressFlight.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
@Service
public interface IMemberRepository extends JpaRepository<Member,Long> {
}
