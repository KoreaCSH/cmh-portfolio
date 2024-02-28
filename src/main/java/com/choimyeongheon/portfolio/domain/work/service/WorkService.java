package com.choimyeongheon.portfolio.domain.work.service;

import com.choimyeongheon.portfolio.domain.work.domain.Work;
import com.choimyeongheon.portfolio.domain.work.repository.WorkRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.works.dto.WorkMapper;
import com.choimyeongheon.portfolio.web.admin.works.dto.WorkSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    // Transaction 관리
    @Transactional
    public Long createWork(WorkSaveRequest request) {
        Work savedWork = workRepository.save(workMapper.toEntity(request));
        return savedWork.getId();
    }

    public Work findById(Long id) {
        return workRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.WORK_NOT_FOUND));
    }

}
