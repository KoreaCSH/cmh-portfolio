package com.choimyeongheon.portfolio.web.admin.homeImage.dto;

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
    private String updatedTitle;

}
