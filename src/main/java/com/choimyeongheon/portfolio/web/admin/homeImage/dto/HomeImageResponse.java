package com.choimyeongheon.portfolio.web.admin.homeImage.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class HomeImageResponse {

    private Long id;
    private String fileName;
    private String originName;
    private String title;
    private LocalDateTime regDate;

}
