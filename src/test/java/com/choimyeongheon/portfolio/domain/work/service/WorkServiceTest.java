package com.choimyeongheon.portfolio.domain.work.service;

import com.choimyeongheon.portfolio.domain.work.domain.Work;
import com.choimyeongheon.portfolio.web.admin.works.dto.WorkResponse;
import com.choimyeongheon.portfolio.web.admin.works.dto.WorkSaveRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkServiceTest {

    @Autowired
    WorkService workService;

    @Value("${portfolio.upload.path}")
    String uploadPath;

    WorkSaveRequest request1;
    WorkSaveRequest request2;
    WorkSaveRequest request3;

    @Test
    void 이미지_저장_성공() {

        Long uploadedWorkId = workService.createWork(request1);

        Work savedWork = workService.findById(uploadedWorkId);

        Assertions.assertThat(savedWork.getOriginName()).isEqualTo(request1.getWork().getOriginalFilename());
        Assertions.assertThat(savedWork.getTitle()).isEqualTo(request1.getTitle());
        Assertions.assertThat(savedWork.getWorkDate()).isEqualTo(request1.getWorkDate());
    }

    @Test
    void work_최신순으로_조회_성공() {

        // 2020
        Long request1Id = workService.createWork(request1);

        // 2019
        Long request2Id = workService.createWork(request2);

        // 2021
        Long request3Id = workService.createWork(request3);

        List<WorkResponse> worksByOrderByWorkDateDesc = workService.findAllByOrderByWorkDateDesc();

        Assertions.assertThat(worksByOrderByWorkDateDesc.get(0).getId()).isEqualTo(request3Id);
        Assertions.assertThat(worksByOrderByWorkDateDesc.get(1).getId()).isEqualTo(request1Id);
        Assertions.assertThat(worksByOrderByWorkDateDesc.get(2).getId()).isEqualTo(request2Id);
    }

    @BeforeAll
    public void setTestCase() throws IOException {

        String originalFileName = "test1.jpeg";
        String filePath = "src/main/resources/static/img/test1.jpeg";
        String title = "title test";
        LocalDate workDate = LocalDate.of(2020, 1, 1);

        MultipartFile multipartFile1 = new MockMultipartFile("image", originalFileName, "image/jpeg",
                new FileInputStream(filePath));

        request1 = new WorkSaveRequest(multipartFile1, title, workDate);


        originalFileName = "test2.jpeg";
        filePath = "src/main/resources/static/img/test2.jpeg";
        title = "title test2";
        workDate = LocalDate.of(2019, 5, 1);

        MultipartFile multipartFile2 = new MockMultipartFile("image", originalFileName, "image/jpeg",
                new FileInputStream(filePath));

        request2 = new WorkSaveRequest(multipartFile2, title, workDate);


        originalFileName = "test3.jpeg";
        filePath = "src/main/resources/static/img/test3.jpeg";
        title = "title test3";
        workDate = LocalDate.of(2021, 10, 1);

        MultipartFile multipartFile3 = new MockMultipartFile("image", originalFileName, "image/jpeg",
                new FileInputStream(filePath));

        request3 = new WorkSaveRequest(multipartFile3, title, workDate);
    }

}