package com.choimyeongheon.portfolio.domain.profile.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.Awards;
import com.choimyeongheon.portfolio.domain.profile.repository.AwardsRepository;
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
public class AwardsService implements ProfileService {

    private final AwardsRepository awardsRepository;

    @Override
    public Long create(ProfileSaveRequest request, Admin admin) {

        Awards awards
                = Awards.builder()
                    .year(request.getYear())
                    .content(request.getContent())
                    .contentEn(request.getContentEn())
                    .createdBy(admin)
                    .build();

        Awards savedAwards = awardsRepository.save(awards);

        return savedAwards.getId();
    }

    @Override
    public void update(ProfileUpdateRequest request, Admin admin) {
        Awards findAwards = awardsRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorType.PROFILE_NOT_FOUND));

        findAwards.update(request.getYear(), request.getContent(), request.getContentEn(), admin);
    }

    @Override
    public List<ProfileResponse> findAll() {
        return awardsRepository.findAll()
                .stream()
                .map(awards -> new ProfileResponse(awards.getYear(), awards.getContent(), awards.getContentEn()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete() {

    }
}
