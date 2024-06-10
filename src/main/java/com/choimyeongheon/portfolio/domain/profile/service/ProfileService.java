package com.choimyeongheon.portfolio.domain.profile.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import com.choimyeongheon.portfolio.domain.profile.repository.ProfileRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileDeletionDto;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileResponse;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileSaveRequest;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
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

    public ProfileUpdateRequest findProfileUpdateRequestById(Long id) {
        Profile findProfile = profileRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.PROFILE_NOT_FOUND));

        return new ProfileUpdateRequest(findProfile);
    }

    public List<ProfileResponse> findAll() {
        return profileRepository.findAllOrderByYear()
                .stream()
                .map(ProfileResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProfileResponse> findAllByProfileType(String profileType) {
        return profileRepository.findAllByProfileTypeOrderByYear(ProfileType.from(profileType))
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

    @Transactional
    public void deleteAllByIds(List<ProfileDeletionDto> profileDeletionDtoList) {
        List<Long> deletedProfileIdList = profileDeletionDtoList.stream()
                                            .filter(ProfileDeletionDto::getIsDeleted)
                                            .map(ProfileDeletionDto::getId)
                                            .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(deletedProfileIdList)) {
            throw new CustomException(ErrorType.EMPTY_PROFILE_DELETION_LIST);
        }

        profileRepository.deleteAllByIds(deletedProfileIdList);
    }

    public List<ProfileDeletionDto> findAllDeletionDto() {
        return profileRepository.findAllOrderByYear()
                .stream()
                .filter(Profile::isNotDeleted)
                .map(ProfileDeletionDto::new)
                .collect(Collectors.toList());
    }

    public List<ProfileDeletionDto> findAllDeletionDtoByProfileType(String profileType) {
        return profileRepository.findAllByProfileTypeOrderByYear(ProfileType.from(profileType))
                .stream()
                .filter(Profile::isNotDeleted)
                .map(ProfileDeletionDto::new)
                .collect(Collectors.toList());
    }

    public List<ProfileType> findAllProfileType() {
        return Arrays.stream(ProfileType.values()).toList();
    }

}
