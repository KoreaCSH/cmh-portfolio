package com.choimyeongheon.portfolio.web.admin.work.dto;

import com.choimyeongheon.portfolio.domain.work.domain.WorkYear;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkYearDeleteDto {

    private Long id;
    private Boolean isDeleted;
    private Integer year;

    public WorkYearDeleteDto(WorkYear workYear) {
        this.id = workYear.getId();
        this.year = workYear.getYear();
        this.isDeleted = false;
    }

}
