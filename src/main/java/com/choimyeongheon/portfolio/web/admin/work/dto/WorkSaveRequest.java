package com.choimyeongheon.portfolio.web.admin.work.dto;

import com.choimyeongheon.portfolio.global.valid.ValidFile;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkSaveRequest {

    @ValidFile
    private MultipartFile work;

    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;
    private LocalDate workDate;

}
