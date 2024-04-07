package com.choimyeongheon.portfolio.web.admin.profile.dto;

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
    private String content;
    private String contentEn;
    private String profileType;

}
