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
    private String typeEn;
    private Integer priority;
    private Boolean isDeleted;

    public ProfileType toEntity(Admin createdBy) {
        return ProfileType.builder()
                .type(this.type)
                .typeEn(this.typeEn)
                .createdBy(createdBy)
                .build();
    }

    public ProfileTypeDto(ProfileType profileType) {
        this.id = profileType.getId();
        this.type = profileType.getType();
        this.typeEn = profileType.getTypeEn();
        this.priority = profileType.getPriority();
        this.isDeleted = false;
    }

}
