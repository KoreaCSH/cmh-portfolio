package com.choimyeongheon.portfolio.web.admin.profile.dto;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileSaveRequest {

    // Integer validation
    private Integer year;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String contentEn;

    // validation
    private String profileType;

    public Profile toEntity(Admin admin) {
        return Profile.builder()
                .year(this.getYear())
                .content(this.getContent())
                .contentEn(this.getContentEn())
                .profileType(ProfileType.valueOf(this.getProfileType()))
                .createdBy(admin)
                .build();
    }

}
