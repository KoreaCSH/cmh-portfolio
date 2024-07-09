package com.choimyeongheon.portfolio.domain.work.service;

import com.choimyeongheon.portfolio.domain.work.domain.WorkYear;
import com.choimyeongheon.portfolio.domain.work.repository.WorkYearRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.work.dto.WorkYearDto;
import com.choimyeongheon.portfolio.web.admin.work.dto.WorkYearRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkYearService {

    private final WorkYearRepository workYearRepository;

    @Transactional
    public void create(WorkYearRequest request) {
        validateWorkYear(request);

        List<WorkYear> workYearList = request.getWorkYearDtoList()
                                    .stream()
                                    .map(WorkYear::new)
                                    .collect(Collectors.toList());

        workYearRepository.saveAll(workYearList);
    }

    public List<WorkYear> findAll() {
        return workYearRepository.findAllByOrderByYear();
    }

    private void validateWorkYear(WorkYearRequest request) {
        Set<Integer> workYearSet = new HashSet<>(workYearRepository.findAll().stream().map(workYear -> workYear.getYear()).collect(Collectors.toList()));
        for (WorkYearDto dto : request.getWorkYearDtoList()) {
            if (dto.getWorkYear() == null || workYearSet.contains(dto.getWorkYear())) {
                throw new CustomException(ErrorType.EMPTY_WORK_YEAR_OR_DUPLICATED);
            }
        }
    }

}
