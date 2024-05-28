package com.choimyeongheon.portfolio.web.admin.work.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class WorkDeleteDto {

    private Long id;
    private String fileName;
    private String originName;
    private String title;
    private LocalDate workDate;
    private Boolean isDeleted;
    private LocalDateTime regDate;

    public WorkDeleteDto(Long id, String fileName, String originName, String title, LocalDate workDate, LocalDateTime regDate) {
        this.id = id;
        this.fileName = fileName;
        this.originName = originName;
        this.title = title;
        this.workDate = workDate;
        this.isDeleted = false;
        this.regDate = regDate;
    }

}
