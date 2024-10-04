package com.choimyeongheon.portfolio.domain.work.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.work.domain.Work;
import com.choimyeongheon.portfolio.domain.work.repository.WorkRepository;
import com.choimyeongheon.portfolio.global.common.DelYn;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.global.util.DateUtil;
import com.choimyeongheon.portfolio.web.admin.work.dto.*;
import com.choimyeongheon.portfolio.web.visitor.work.dto.VisitorWorkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final WorkMapper workMapper;

    // Transaction 관리
    @Transactional
    public Long createWork(WorkSaveRequest request, Admin admin) {
        Work savedWork = workRepository.save(workMapper.toEntity(request, admin));
        return savedWork.getId();
    }

    // 모두 조회 - 최신순으로 정렬
    public List<WorkResponse> findAllByOrderByWorkDateDesc() {
        return workRepository.findAllByOrderByWorkDateDesc()
                .stream()
                .filter(work -> work.getDelYn() == DelYn.N)
                .map(workMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 연도별 조회 - 최신순으로 정렬 (매개변수 WorkDate - YYYY)
    public List<WorkResponse> findByYearOrderByWorkDateDesc(int year) {
        return workRepository.findByYearOrderByWorkDateDesc(year)
                .stream()
                .filter(work -> work.getDelYn() == DelYn.N)
                .map(workMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 수정 - title, workDate 변경 가능
    @Transactional
    public void update(WorkUpdateRequest request, Admin admin) {
        Work findWork = findById(request.getId());
        findWork.updateTitleAndWorkDate(request.getUpdatedTitle(), DateUtil.stringToLocalDate(request.getUpdatedWorkDate()), admin);
    }

    public WorkUpdateRequest findWorkUpdateRequestById(Long id) {
        Work work = findById(id);
        return new WorkUpdateRequest(work);
    }

    // 삭제 - 일괄 삭제 가능
    public List<WorkDeleteDto> findAllDeleteDto() {
        return workRepository.findAllByOrderByWorkDateDesc()
                        .stream()
                        .filter(work -> work.getDelYn() == DelYn.N)
                        .map(WorkDeleteDto::new)
                        .collect(Collectors.toList());
    }

    public List<WorkDeleteDto> findAllDeleteDtoByYearOrderByWorkDateDesc(int year) {
        return workRepository.findByYearOrderByWorkDateDesc(year)
                .stream()
                .filter(work -> work.getDelYn() == DelYn.N)
                .map(WorkDeleteDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAllByIds(List<WorkDeleteDto> workDeleteDtoList) {

        List<Long> deletedWorkIdList = workDeleteDtoList
                                                    .stream()
                                                    .filter(WorkDeleteDto::getIsDeleted)
                                                    .map(WorkDeleteDto::getId)
                                                    .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(deletedWorkIdList)) {
            throw new CustomException(ErrorType.EMPTY_IMAGE_DELETION_LIST);
        }

        workRepository.deleteAllByIds(deletedWorkIdList);
    }

    public Work findById(Long id) {
        return workRepository.findNotDeletedById(id)
                .orElseThrow(() -> new CustomException(ErrorType.WORK_NOT_FOUND));
    }

    public ResponseEntity<Resource> display(String fileName) {
        return workMapper.toResource(fileName);
    }

    public List<VisitorWorkResponse> findAllPublicWorkResponse() {
        return workRepository.findAllByOrderByWorkDateDesc()
                .stream()
                .filter(work -> work.getDelYn() == DelYn.N)
                .map(VisitorWorkResponse::new)
                .collect(Collectors.toList());
    }

    public List<VisitorWorkResponse> findPublicWorkResponseByWorkYear(Integer workYear) {
        return workRepository.findByYearOrderByWorkDateDesc(workYear)
                .stream()
                .filter(work -> work.getDelYn() == DelYn.N)
                .map(VisitorWorkResponse::new)
                .collect(Collectors.toList());
    }

    // API 단건 조회
    public VisitorWorkResponse findVisitorWorkResponseById(Long id) {
        return new VisitorWorkResponse(findById(id));
    }

}
