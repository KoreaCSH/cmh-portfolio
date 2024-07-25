package com.choimyeongheon.portfolio.web.admin.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileTypeRequest {

    private List<ProfileTypeDto> profileTypeDtoList;

}
