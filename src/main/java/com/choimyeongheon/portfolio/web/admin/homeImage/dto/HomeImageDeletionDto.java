package com.choimyeongheon.portfolio.web.admin.homeImage.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class HomeImageDeletionDto {

    private Long id;
    private String fileName;
    private String title;
    private boolean isDeleted;

    public HomeImageDeletionDto(Long id, String fileName, String title) {
        this.id = id;
        this.fileName = fileName;
        this.title = title;
        this.isDeleted = false;
    }

}
