package com.choimyeongheon.portfolio.domain.profile.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.SoloExhibitions;
import com.choimyeongheon.portfolio.domain.profile.repository.SoloExhibitionsRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileResponse;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileSaveRequest;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SoloExhibitionsService implements ProfileService {

    private final SoloExhibitionsRepository soloExhibitionsRepository;

    @Override
    public Long create(ProfileSaveRequest request, Admin admin) {

        SoloExhibitions soloExhibitions
                = SoloExhibitions.builder()
                    .year(request.getYear())
                    .content(request.getContent())
                    .contentEn(request.getContentEn())
                    .createdBy(admin)
                    .build();

        SoloExhibitions savedSoloExhibitions = soloExhibitionsRepository.save(soloExhibitions);

        return savedSoloExhibitions.getId();
    }

    @Override
    public void update(ProfileUpdateRequest request, Admin admin) {
        SoloExhibitions findSoloExhibitions = soloExhibitionsRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorType.PROFILE_NOT_FOUND));

        findSoloExhibitions.update(request.getYear(), request.getContent(), request.getContentEn(), admin);
    }

    @Override
    public List<ProfileResponse> findAll() {
        return soloExhibitionsRepository.findAll()
                .stream()
                .map(soloExhibitions -> new ProfileResponse(soloExhibitions.getYear(), soloExhibitions.getContent(), soloExhibitions.getContentEn()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete() {

    }
}
