package com.choimyeongheon.portfolio.web.home.dto;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HomeImageRequest {

    private MultipartFile homeImage;
    private String title;

}
