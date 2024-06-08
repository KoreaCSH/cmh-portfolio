package com.choimyeongheon.portfolio.web.admin.work.dto;

import com.choimyeongheon.portfolio.domain.work.domain.Work;
import com.choimyeongheon.portfolio.global.util.DateUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class WorkUpdateRequest {

    private Long id;
    private String originName;

    @NotBlank(message = "제목을 입력해 주세요.")
    private String updatedTitle;
    private String updatedWorkDate;

    public WorkUpdateRequest(Work work){
        this.id = work.getId();
        this.originName = work.getOriginName();
        this.updatedTitle = work.getTitle();
        this.updatedWorkDate = DateUtil.yyyyMMdd(work.getWorkDate());
    }

}
