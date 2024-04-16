package com.choimyeongheon.portfolio.domain.work.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.work.domain.Work;
import com.choimyeongheon.portfolio.web.admin.work.dto.WorkResponse;
import com.choimyeongheon.portfolio.web.admin.work.dto.WorkSaveRequest;
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
import java.util.List;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WorkServiceTest {

    @Autowired
    WorkService workService;

    @Value("${portfolio.upload.path}")
    String uploadPath;

    Admin admin;

    WorkSaveRequest request1;
    WorkSaveRequest request2;
    WorkSaveRequest request3;
    WorkSaveRequest request4;
    WorkSaveRequest request5;

    @Test
    void 이미지_저장_성공() {

        Long uploadedWorkId = workService.createWork(request1, admin);

        Work savedWork = workService.findById(uploadedWorkId);

        Assertions.assertThat(savedWork.getOriginName()).isEqualTo(request1.getWork().getOriginalFilename());
        Assertions.assertThat(savedWork.getTitle()).isEqualTo(request1.getTitle());
        // Assertions.assertThat(savedWork.getWorkDate()).isEqualTo(request1.getWorkDate());
    }

    @Test
    void work_최신순으로_전체조회_성공() {

        // 2020
        Long request1Id = workService.createWork(request1, admin);

        // 2019
        Long request2Id = workService.createWork(request2, admin);

        // 2021
        Long request3Id = workService.createWork(request3, admin);

        List<WorkResponse> worksByOrderByWorkDateDesc = workService.findAllByOrderByWorkDateDesc();

        System.out.println(worksByOrderByWorkDateDesc.size());
        worksByOrderByWorkDateDesc.forEach(work -> System.out.println(work.getId()));
        Assertions.assertThat(worksByOrderByWorkDateDesc.get(0).getId()).isEqualTo(request3Id);
        Assertions.assertThat(worksByOrderByWorkDateDesc.get(1).getId()).isEqualTo(request1Id);
        Assertions.assertThat(worksByOrderByWorkDateDesc.get(2).getId()).isEqualTo(request2Id);
    }

    @Test
    void work_최신순으로_특정연도_조회_성공() {

        // 2020
        Long request1Id = workService.createWork(request1, admin);

        // 2019
        Long request2Id = workService.createWork(request2, admin);

        // 2021 - 10
        Long request3Id = workService.createWork(request3, admin);
        // 2021 - 11
        Long request4Id = workService.createWork(request4, admin);
        // 2021 - 01
        Long request5Id = workService.createWork(request5, admin);

        List<WorkResponse> worksByYearOrderByWorkDateDesc = workService.findByYearOrderByWorkDateDesc(2021);

        Assertions.assertThat(worksByYearOrderByWorkDateDesc.size()).isEqualTo(3);
        Assertions.assertThat(worksByYearOrderByWorkDateDesc.get(0).getId()).isEqualTo(request4Id);
        Assertions.assertThat(worksByYearOrderByWorkDateDesc.get(1).getId()).isEqualTo(request3Id);
        Assertions.assertThat(worksByYearOrderByWorkDateDesc.get(2).getId()).isEqualTo(request5Id);
    }

    @BeforeAll
    public void setTestCase() throws IOException {

        String originalFileName = "test1.jpeg";
        String filePath = "src/main/resources/static/img/test1.jpeg";
        String title = "title test";
        String workDate = "2020-01";

        MultipartFile multipartFile1 = new MockMultipartFile("image", originalFileName, "image/jpeg",
                new FileInputStream(filePath));

        request1 = new WorkSaveRequest(multipartFile1, title, workDate);


        originalFileName = "test2.jpeg";
        filePath = "src/main/resources/static/img/test2.jpeg";
        title = "title test2";
        workDate = "2019-05";

        MultipartFile multipartFile2 = new MockMultipartFile("image", originalFileName, "image/jpeg",
                new FileInputStream(filePath));

        request2 = new WorkSaveRequest(multipartFile2, title, workDate);


        originalFileName = "test3.jpeg";
        filePath = "src/main/resources/static/img/test3.jpeg";
        title = "title test3";
        workDate = "2021-10";

        MultipartFile multipartFile3 = new MockMultipartFile("image", originalFileName, "image/jpeg",
                new FileInputStream(filePath));

        request3 = new WorkSaveRequest(multipartFile3, title, workDate);

        request4 = new WorkSaveRequest(multipartFile3, title, "2021-11");
        request5 = new WorkSaveRequest(multipartFile3, title, "2021-01");
    }

}