package com.choimyeongheon.portfolio.web.admin.homeImage.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class HomeImageResponse {

    private String fileName;
    private String title;

}
