package com.choimyeongheon.portfolio.web.admin.work.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WorkYearDeleteRequest {

    List<WorkYearDeleteDto> workYearDeleteDtoList;

}
