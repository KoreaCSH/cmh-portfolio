package com.choimyeongheon.portfolio.domain.work.repository;

import com.choimyeongheon.portfolio.domain.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Long> {

    List<Work> findAllByOrderByWorkDateDesc();

}
