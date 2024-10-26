package com.choimyeongheon.portfolio.web.admin.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordUpdateRequest {

    private Long id;

    @NotBlank(message = "기존 비밀번호를 입력해 주세요.")
    private String prevPassword;

    @NotBlank(message = "새 비밀번호를 입력해 주세요.")
    private String newPassword;

    public PasswordUpdateRequest(Long id) {
        this.id = id;
    }

}
