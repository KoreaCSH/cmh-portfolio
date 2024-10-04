package com.choimyeongheon.portfolio.web.admin.work.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class WorkResponse {

    private Long id;
    private String fileName;
    private String originName;
    private String title;
    private LocalDate workDate;
    private String regDate;

}
