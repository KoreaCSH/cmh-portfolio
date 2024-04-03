package com.choimyeongheon.portfolio.domain.profile.repository;

import com.choimyeongheon.portfolio.domain.profile.domain.Awards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardsRepository extends JpaRepository<Awards, Long> {
}
