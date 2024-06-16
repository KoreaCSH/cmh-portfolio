package com.choimyeongheon.portfolio.web.visitor.profile.dto;

import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VisitorProfileResponse {

    private Integer year;
    private String content;
    private String contentEn;
    private String profileType;

    public VisitorProfileResponse(Profile profile) {
        this.year = profile.getYear();
        this.content = profile.getContent();
        this.contentEn = profile.getContentEn();
        this.profileType = profile.getProfileType().getType();
    }

}
