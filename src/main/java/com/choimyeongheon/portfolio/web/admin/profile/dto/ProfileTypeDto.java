package com.choimyeongheon.portfolio.web.admin.profile.dto;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileTypeE;
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

    public ProfileTypeE toEntity(Admin createdBy) {
        return ProfileTypeE.builder()
                .type(this.type)
                .createdBy(createdBy)
                .build();
    }

    public ProfileTypeDto(ProfileTypeE profileType) {
        this.id = profileType.getId();
        this.type = profileType.getType();
        this.isDeleted = false;
    }

}
