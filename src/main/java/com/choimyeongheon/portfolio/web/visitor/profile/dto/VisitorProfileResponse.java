package com.choimyeongheon.portfolio.web.visitor.profile.dto;

import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileSprCd;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VisitorProfileResponse {

    private String year;
    private String content;
    private String contentEn;
    private String profileType;

    public static VisitorProfileResponse createVisitorProfileResponse(Profile profile) {
        VisitorProfileResponse response = new VisitorProfileResponse();
        if (profile.getProfileSprCd() == ProfileSprCd.NORMAL) {
            response.year = String.valueOf(profile.getYear());
            response.content = profile.getContent();
            response.contentEn = profile.getContentEn();
            response.profileType = profile.getProfileType().getType();
        } else {
            response.year = profile.getFromYear() + " - " + profile.getToYear();
            response.content = profile.getContent();
            response.contentEn = profile.getContentEn();
            response.profileType = profile.getProfileType().getType();
        }
        return response;
    }

}
