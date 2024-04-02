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
public class ProfileSaveRequest {

    // Integer validation
    private Integer year;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

}
