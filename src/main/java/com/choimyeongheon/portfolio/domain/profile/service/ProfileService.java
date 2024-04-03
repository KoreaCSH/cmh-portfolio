package com.choimyeongheon.portfolio.domain.profile.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileResponse;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileSaveRequest;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileUpdateRequest;

import java.util.List;

public interface ProfileService {

    Long create(ProfileSaveRequest request, Admin admin);

    void update(ProfileUpdateRequest request, Admin admin);


    List<ProfileResponse> findAll();

    void delete();

}
