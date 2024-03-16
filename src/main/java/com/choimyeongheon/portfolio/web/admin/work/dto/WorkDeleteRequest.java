package com.choimyeongheon.portfolio.web.admin.work.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class WorkDeleteRequest {

    private List<WorkDeleteDto> workDeleteDtoList;

}
