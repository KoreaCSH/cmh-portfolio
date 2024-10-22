package com.choimyeongheon.portfolio.web.admin.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestApiDto {

    @NotBlank(message = "ID를 입력해 주세요.")
    private String userId;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String userName;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;

}
