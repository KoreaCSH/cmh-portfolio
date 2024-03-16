package com.choimyeongheon.portfolio.web.admin.work.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class WorkDeleteDto {

    private Long id;
    private String fileName;
    private String title;
    private LocalDate workDate;
    private Boolean isDeleted;

    public WorkDeleteDto(Long id, String fileName, String title, LocalDate workDate) {
        this.id = id;
        this.fileName = fileName;
        this.title = title;
        this.workDate = workDate;
        this.isDeleted = false;
    }

}
