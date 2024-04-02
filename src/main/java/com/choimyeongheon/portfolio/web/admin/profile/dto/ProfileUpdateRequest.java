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

    private Long id;
    private Integer year;
    private String content;

}
