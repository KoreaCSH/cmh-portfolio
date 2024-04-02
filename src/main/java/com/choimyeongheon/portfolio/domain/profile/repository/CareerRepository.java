package com.choimyeongheon.portfolio.domain.profile.repository;

import com.choimyeongheon.portfolio.domain.profile.domain.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long> {
}
