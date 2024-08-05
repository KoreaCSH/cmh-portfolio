package com.choimyeongheon.portfolio.web.admin.profile.dto;

import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfileUpdateRequest {

    // Validation 필요
    private Long id;
    private Integer year;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String contentEn;
    private Long profileType;

    public ProfileUpdateRequest(Profile profile) {
        this.id = profile.getId();
        this.year = profile.getYear();
        this.content = profile.getContent();
        this.contentEn = profile.getContentEn();
        this.profileType = profile.getProfileType().getId();
    }

}
