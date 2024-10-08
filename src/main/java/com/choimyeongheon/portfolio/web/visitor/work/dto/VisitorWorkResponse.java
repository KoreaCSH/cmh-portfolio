package com.choimyeongheon.portfolio.web.visitor.work.dto;

import com.choimyeongheon.portfolio.domain.work.domain.Work;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VisitorWorkResponse {

    private String fileName;
    private String title;
    private String year;

    public VisitorWorkResponse(Work work) {
        this.fileName = work.getFileName();
        this.title = work.getTitle();
        this.year = String.valueOf(work.getWorkDate().getYear());
    }

}
