package com.choimyeongheon.portfolio.web.admin.work.dto;

import com.choimyeongheon.portfolio.domain.work.domain.Work;
import com.choimyeongheon.portfolio.global.util.DateUtil;
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
    private String regDate;

    public WorkDeleteDto(Work work) {
        this.id = work.getId();
        this.fileName = work.getFileName();
        this.originName = work.getOriginName();
        this.title = work.getTitle();
        this.workDate = work.getWorkDate();
        this.isDeleted = false;
        this.regDate = work.getUpdatedAt() == null ? DateUtil.yyyyMMddHHmm(work.getCreatedAt()) : DateUtil.yyyyMMddHHmm(work.getUpdatedAt());
    }

}
