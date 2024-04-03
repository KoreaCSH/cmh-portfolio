package com.choimyeongheon.portfolio.domain.profile.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.GroupExhibitions;
import com.choimyeongheon.portfolio.domain.profile.repository.GroupExhibitionsRepository;
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
public class GroupExhibitionsService implements ProfileService {

    private final GroupExhibitionsRepository groupExhibitionsRepository;

    @Override
    public Long create(ProfileSaveRequest request, Admin admin) {

        GroupExhibitions groupExhibitions
                = GroupExhibitions.builder()
                .year(request.getYear())
                .content(request.getContent())
                .contentEn(request.getContentEn())
                .createdBy(admin)
                .build();

        GroupExhibitions savedGroupExhibitions = groupExhibitionsRepository.save(groupExhibitions);

        return savedGroupExhibitions.getId();
    }

    @Override
    public void update(ProfileUpdateRequest request, Admin admin) {
        GroupExhibitions findGroupExhibitions = groupExhibitionsRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorType.PROFILE_NOT_FOUND));

        findGroupExhibitions.update(request.getYear(), request.getContent(), request.getContentEn(), admin);
    }

    @Override
    public List<ProfileResponse> findAll() {
        return groupExhibitionsRepository.findAll()
                .stream()
                .map(groupExhibitions -> new ProfileResponse(groupExhibitions.getYear(), groupExhibitions.getContent(), groupExhibitions.getContentEn()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete() {

    }
}
