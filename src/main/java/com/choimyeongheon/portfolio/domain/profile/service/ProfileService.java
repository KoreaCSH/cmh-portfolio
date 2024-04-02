package com.choimyeongheon.portfolio.domain.profile.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileSaveRequest;

import java.util.List;

public interface ProfileService {

    Long create(ProfileSaveRequest request, Admin admin);

    void update();


    List<Profile> findAll();

    void delete();

}
