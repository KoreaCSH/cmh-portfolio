package com.choimyeongheon.portfolio.web.admin.work.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkYearDto {

    @NotBlank(message = "연도를 입력해주세요.")
    private Integer workYear;

}
