package com.choimyeongheon.portfolio.web.admin.profile.dto;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileSprCd;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileFromToSaveRequest {

    @NotNull(message = "연도를 입력해 주세요.")
    @Min(value = 1995, message = "1995 이하 숫자는 입력할 수 없습니다.")
    private Integer fromYear;

    @NotNull(message = "연도를 입력해 주세요.")
    @Min(value = 1995, message = "1995 이하 숫자는 입력할 수 없습니다.")
    private Integer toYear;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String contentEn;

    @NotNull(message = "타입을 지정해 주세요.")
    private Long profileTypeId;

    public Profile toEntity(Admin admin, ProfileType profileType) {
        return Profile.builder()
                .fromYear(this.getFromYear())
                .toYear(this.getToYear())
                .year(this.getFromYear())
                .content(this.getContent())
                .contentEn(this.getContentEn())
                .profileSprCd(ProfileSprCd.FROM_TO)
                .profileType(profileType)
                .createdBy(admin)
                .build();
    }

}