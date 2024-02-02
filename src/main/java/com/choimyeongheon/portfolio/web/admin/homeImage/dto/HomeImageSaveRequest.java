package com.choimyeongheon.portfolio.web.admin.homeImage.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HomeImageSaveRequest {

    private MultipartFile homeImage;

    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

}
