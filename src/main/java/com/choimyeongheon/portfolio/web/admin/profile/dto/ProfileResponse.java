package com.choimyeongheon.portfolio.web.admin.profile.dto;

import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProfileResponse {

    private Long id;
    private Integer year;
    private String content;
    private String contentEn;
    private String profileType;

    public ProfileResponse(Profile profile) {
        this.id = profile.getId();
        this.year = profile.getYear();
        this.content = profile.getContent();
        this.contentEn = profile.getContentEn();
        this.profileType = profile.getProfileType().getType();
    }

}
