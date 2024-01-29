package com.choimyeongheon.portfolio.web.admin.homeImage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HomeImageUpdateRequest {

    private Long id;

    @NotBlank(message = "제목을 입력해 주세요.")
    private String updatedTitle;

}
