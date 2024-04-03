package com.choimyeongheon.portfolio.web.admin.profile.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ProfileResponse {

    private Integer year;
    private String content;
    private String contentEn;

}
