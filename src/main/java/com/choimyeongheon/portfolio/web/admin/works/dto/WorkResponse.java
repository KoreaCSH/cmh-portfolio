package com.choimyeongheon.portfolio.web.admin.works.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class WorkResponse {

    private Long id;
    private String fileName;
    private String title;
    private LocalDate workDate;

}
