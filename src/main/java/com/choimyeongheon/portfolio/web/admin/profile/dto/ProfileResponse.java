package com.choimyeongheon.portfolio.web.admin.profile.dto;

import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileSprCd;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProfileResponse {

    private Long id;
    private String year;
    private String content;
    private String contentEn;
    private String profileType;
    private String profileSprCd;

    public static ProfileResponse createProfileResponse(Profile profile) {
        ProfileResponse response = new ProfileResponse();
        if (profile.getProfileSprCd() == ProfileSprCd.NORMAL) {
            response.id = profile.getId();
            response.year = String.valueOf(profile.getYear());
            response.content = profile.getContent();
            response.contentEn = profile.getContentEn();
            response.profileType = profile.getProfileType().getType();
            response.profileSprCd = profile.getProfileSprCd().name();
        }
        else {
            response.id = profile.getId();
            response.year = profile.getFromYear() + " - " + profile.getToYear();
            response.content = profile.getContent();
            response.contentEn = profile.getContentEn();
            response.profileType = profile.getProfileType().getType();
            response.profileSprCd = profile.getProfileSprCd().name();
        }
        return response;
    }

}
