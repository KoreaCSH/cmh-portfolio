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
import com.choimyeongheon.portfolio.web.visitor.profile.dto.VisitorProfileResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileTypeService profileTypeService;

    @Transactional
    public void create(ProfileSaveRequest request, Admin admin) {

        ProfileType findProfileType = profileTypeService.findById(request.getProfileTypeId());

        Profile profile = request.toEntity(admin, findProfileType);
        profileRepository.save(profile);
    }

    @Transactional
    public void update(ProfileUpdateRequest request, Admin admin) {
        Profile findProfile = profileRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorType.PROFILE_NOT_FOUND));

        ProfileType beforeProfileType = profileTypeService.findById(findProfile.getProfileType().getId());
        ProfileType afterProfileType = profileTypeService.findById(request.getProfileType());

        findProfile.update(request, beforeProfileType, afterProfileType, admin);
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

    public List<VisitorProfileResponse> findAllVisitorProfileResponse() {
        return profileRepository.findAllOrderByYear()
                .stream()
                .map(VisitorProfileResponse::new)
                .collect(Collectors.toList());
    }

    public List<ProfileResponse> findAllByProfileTypeId(Long profileTypeId) {

        ProfileType profileType = profileTypeService.findById(profileTypeId);

        return profileRepository.findAllByProfileTypeOrderByYear(profileType)
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
    public void deleteAllByIds(List<ProfileDeletionDto> profileDeletionDtoList, Admin admin) {
        List<Long> deletedProfileIdList = profileDeletionDtoList.stream()
                                            .filter(ProfileDeletionDto::getIsDeleted)
                                            .map(ProfileDeletionDto::getId)
                                            .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(deletedProfileIdList)) {
            throw new CustomException(ErrorType.EMPTY_PROFILE_DELETION_LIST);
        }

        profileRepository.deleteAllByIds(deletedProfileIdList, admin);
    }

    public List<ProfileDeletionDto> findAllDeletionDto() {
        return profileRepository.findAllOrderByYear()
                .stream()
                .filter(Profile::isNotDeleted)
                .map(ProfileDeletionDto::new)
                .collect(Collectors.toList());
    }

    public List<ProfileDeletionDto> findAllDeletionDtoByProfileTypeId(Long profileTypeId) {

        ProfileType profileType = profileTypeService.findById(profileTypeId);

        return profileRepository.findAllByProfileTypeOrderByYear(profileType)
                .stream()
                .filter(Profile::isNotDeleted)
                .map(ProfileDeletionDto::new)
                .collect(Collectors.toList());
    }

}
