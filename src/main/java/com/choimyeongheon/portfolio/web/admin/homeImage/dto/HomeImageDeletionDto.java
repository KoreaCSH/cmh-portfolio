package com.choimyeongheon.portfolio.web.admin.homeImage.dto;

import com.choimyeongheon.portfolio.global.util.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class HomeImageDeletionDto {

    private Long id;
    private String fileName;
    private String originName;
    private String title;
    private Boolean isDeleted;
    private String regDate;

    public HomeImageDeletionDto(Long id, String fileName, String originName, String title, LocalDateTime regDate) {
        this.id = id;
        this.fileName = fileName;
        this.originName = originName;
        this.title = title;
        this.isDeleted = false;
        this.regDate = DateUtil.yyyyMMddHHmm(regDate);
    }

}
