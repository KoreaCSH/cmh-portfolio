package com.choimyeongheon.portfolio.web.admin.homeImage.dto;


import com.choimyeongheon.portfolio.global.valid.ValidFile;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HomeImageSaveRequest {

    @ValidFile(message = "저장할 파일을 선택해주세요.")
    private MultipartFile homeImage;

    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

}
