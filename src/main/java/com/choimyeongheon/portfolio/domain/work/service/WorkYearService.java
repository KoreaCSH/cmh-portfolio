package com.choimyeongheon.portfolio.domain.work.service;

import com.choimyeongheon.portfolio.domain.work.domain.WorkYear;
import com.choimyeongheon.portfolio.domain.work.repository.WorkYearRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkYearService {

    private final WorkYearRepository workYearRepository;

    public List<WorkYear> findAll() {
        return workYearRepository.findAll();
    }

}
