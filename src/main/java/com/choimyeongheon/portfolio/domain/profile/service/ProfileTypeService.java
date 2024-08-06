package com.choimyeongheon.portfolio.domain.profile.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import com.choimyeongheon.portfolio.domain.profile.repository.ProfileTypeRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileTypeDto;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileTypeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProfileTypeService {

    private final ProfileTypeRepository profileTypeRepository;

    @Transactional
    public void create(ProfileTypeRequest request, Admin admin) {
        validateProfileType(request);

        List<ProfileType> profileTypeList = request.getProfileTypeDtoList()
                                            .stream()
                                            .map(dto -> dto.toEntity(admin))
                                            .collect(Collectors.toList());

        profileTypeRepository.saveAll(profileTypeList);
    }

    @Transactional
    public void update(ProfileTypeDto dto, Admin updatedBy) {
        ProfileType findProfileType = this.findById(dto.getId());
        findProfileType.update(dto.getType(), dto.getTypeEn(), updatedBy);
    }

    @Transactional
    public void deleteAll(ProfileTypeRequest request, Admin updatedBy) {
        List<Long> deletedProfileTypeIdList = request.getProfileTypeDtoList().stream()
                                                .filter(ProfileTypeDto::getIsDeleted)
                                                .map(ProfileTypeDto::getId)
                                                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(deletedProfileTypeIdList)) {
            throw new CustomException(ErrorType.EMPTY_PROFILE_TYPE_LIST);
        }

        profileTypeRepository.findAllById(deletedProfileTypeIdList)
                .stream()
                .forEach(profileType -> profileType.delete(updatedBy));
    }

    public List<ProfileTypeDto> findAllDto() {
        return this.findAll()
                .stream()
                .map(ProfileTypeDto::new)
                .collect(Collectors.toList());
    }

    public ProfileTypeDto findDto(Long id) {
        ProfileType findProfileType = this.findById(id);
        return new ProfileTypeDto(findProfileType);
    }

    private List<ProfileType> findAll() {
        return profileTypeRepository.findAll();
    }

    public ProfileType findById(Long id) {
        return profileTypeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.PROFILE_TYPE_NOT_FOUND));
    }

    private void validateProfileType(ProfileTypeRequest request) {
        Set<String> profileTypeSet = new HashSet<>(this.findAll().stream().map(type -> type.getType()).collect(Collectors.toList()));
        for (ProfileTypeDto dto : request.getProfileTypeDtoList()) {
            if (dto.getType() == null || dto.getType().equals("") || profileTypeSet.contains(dto.getType())) {
                throw new CustomException(ErrorType.EMPTY_PROFILE_TYPE_OR_DUPLICATED);
            }
        }
    }

}
