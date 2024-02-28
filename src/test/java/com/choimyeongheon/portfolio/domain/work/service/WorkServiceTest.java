package com.choimyeongheon.portfolio.domain.work.service;

import com.choimyeongheon.portfolio.domain.work.domain.Work;
import com.choimyeongheon.portfolio.web.admin.works.dto.WorkSaveRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WorkServiceTest {

    @Autowired
    WorkService workService;

    @Value("${portfolio.upload.path}")
    String uploadPath;

    @Test
    void 이미지_저장_성공() throws IOException {

        String originalFileName = "test1.jpeg";
        String filePath = "src/main/resources/static/img/test1.jpeg";
        String title = "title test";
        LocalDate workDate = LocalDate.of(2020, 1, 1);

        MultipartFile multipartFile = new MockMultipartFile("image", originalFileName, "image/jpeg",
                new FileInputStream(filePath));

        WorkSaveRequest request = new WorkSaveRequest(multipartFile, title, workDate);
        Long uploadedWorkId = workService.createWork(request);

        Work savedWork = workService.findById(uploadedWorkId);

        Assertions.assertThat(savedWork.getOriginName()).isEqualTo(originalFileName);
        Assertions.assertThat(savedWork.getTitle()).isEqualTo(title);
        Assertions.assertThat(savedWork.getWorkDate()).isEqualTo(workDate);
    }

}