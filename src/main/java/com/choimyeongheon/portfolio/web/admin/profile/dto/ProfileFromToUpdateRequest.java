package com.choimyeongheon.portfolio.web.admin.profile.dto;

import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProfileFromToUpdateRequest {

    private Long id;
    private Integer year;
    private Integer fromYear;
    private Integer toYear;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String contentEn;
    private Long profileType;

    public ProfileFromToUpdateRequest(Profile profile) {
        this.id = profile.getId();
        this.fromYear = profile.getFromYear();
        this.toYear = profile.getToYear();
        this.content = profile.getContent();
        this.contentEn = profile.getContentEn();
        this.profileType = profile.getProfileType().getId();
    }

}
