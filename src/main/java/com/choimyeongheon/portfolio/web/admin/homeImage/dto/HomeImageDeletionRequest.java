package com.choimyeongheon.portfolio.web.admin.homeImage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class HomeImageDeletionRequest {

    private List<HomeImageDeletionDto> homeImageDeletionDtoList;

}
