package com.choimyeongheon.portfolio.web.admin.profile.dto;

import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfileDeletionDto {

    private Long id;
    private Integer year;
    private String content;
    private String contentEn;
    private String profileType;
    private Boolean isDeleted;

    public ProfileDeletionDto(Profile profile) {
        this.id = profile.getId();
        this.year = profile.getYear();
        this.content = profile.getContent();
        this.contentEn = profile.getContentEn();
        this.profileType = profile.getProfileType().getType();
        this.isDeleted = false;
    }


}
