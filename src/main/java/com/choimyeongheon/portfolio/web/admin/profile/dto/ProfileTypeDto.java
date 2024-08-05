package com.choimyeongheon.portfolio.web.admin.profile.dto;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfileTypeDto {

    private Long id;
    private String type;
    private Boolean isDeleted;

    public ProfileType toEntity(Admin createdBy) {
        return ProfileType.builder()
                .type(this.type)
                .createdBy(createdBy)
                .build();
    }

    public ProfileTypeDto(ProfileType profileType) {
        this.id = profileType.getId();
        this.type = profileType.getType();
        this.isDeleted = false;
    }

}
