package com.choimyeongheon.portfolio.domain.work.repository;

import com.choimyeongheon.portfolio.domain.work.domain.WorkYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkYearRepository extends JpaRepository<WorkYear, Long> {
}
