package com.choimyeongheon.portfolio.domain.profile.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import com.choimyeongheon.portfolio.domain.profile.repository.ProfileRepository;
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
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public void create(ProfileSaveRequest request, Admin admin) {
        Profile profile = request.toEntity(admin);
        profileRepository.save(profile);
    }

    @Transactional
    public void update(ProfileUpdateRequest request, Admin admin) {
        Profile findProfile = profileRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorType.PROFILE_NOT_FOUND));

        findProfile.update(request, admin);
    }


    public List<ProfileResponse> findAll() {
        return profileRepository.findAllOrderByYear()
                .stream()
                .map(ProfileResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Profile findProfile = profileRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.PROFILE_NOT_FOUND));

        findProfile.delete();
    }

    public List<String> findAllProfileType() {
        return ProfileType.getValues();
    }

}
