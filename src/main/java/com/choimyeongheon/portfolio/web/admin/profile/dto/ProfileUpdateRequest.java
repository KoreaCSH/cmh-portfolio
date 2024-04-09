package com.choimyeongheon.portfolio.web.admin.profile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
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
    private String profileType;

}
