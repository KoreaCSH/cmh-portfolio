package com.choimyeongheon.portfolio.domain.profile.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.Career;
import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.domain.profile.repository.CareerRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileSaveRequest;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CareerService implements ProfileService {

    private final CareerRepository careerRepository;

    @Override
    @Transactional
    public Long create(ProfileSaveRequest request, Admin admin) {

        Career career = Career.builder()
                            .year(request.getYear())
                            .content(request.getContent())
                            .createdBy(admin)
                            .build();

        Career savedCareer = careerRepository.save(career);

        return savedCareer.getId();
    }

    @Override
    @Transactional
    public void update(ProfileUpdateRequest request, Admin admin) {
        Career findCareer = careerRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorType.PROFILE_NOT_FOUND));

        findCareer.update(request.getYear(), request.getContent(), admin);
    }

    @Override
    public List<Profile> findAll() {
        return null;
    }

    @Override
    @Transactional
    public void delete() {

    }

}
