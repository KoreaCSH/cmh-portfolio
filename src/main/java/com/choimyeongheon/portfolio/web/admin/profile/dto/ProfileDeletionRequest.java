package com.choimyeongheon.portfolio.web.admin.profile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProfileDeletionRequest {

    private List<ProfileDeletionDto> profileDeletionDtoList;

}
