package com.choimyeongheon.portfolio.domain.homeImage.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.homeImage.domain.HomeImage;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageSaveRequest;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageUpdateRequest;
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

@SpringBootTest
@Transactional
class HomeImageServiceTest {

    @Autowired
    HomeImageService homeImageService;

    @Value("${portfolio.upload.path}")
    String uploadPath;

    Admin admin = null;

    @Test
    // @Rollback(value = true)
    void 이미지_저장_성공() throws IOException {

        String originalFileName = "test1.jpeg";
        String filePath = "src/main/resources/static/img/test1.jpeg";
        String title = "title test";
        MultipartFile multipartFile = new MockMultipartFile("image", originalFileName, "image/jpeg",
                                                            new FileInputStream(filePath));

        HomeImageSaveRequest request = new HomeImageSaveRequest(multipartFile, title);
        Long uploadedHomeImageId = homeImageService.createHomeImage(request, admin);

        HomeImage findHomeImage = homeImageService.findById(uploadedHomeImageId);

        Assertions.assertThat(findHomeImage.getOriginName()).isEqualTo(originalFileName);
        Assertions.assertThat(findHomeImage.getTitle()).isEqualTo(title);
    }

    @Test
    void 이미지_제목_수정_성공() throws IOException {

        String originalFileName = "test1.jpeg";
        String filePath = "src/main/resources/static/img/test1.jpeg";
        String title = "title test";
        MultipartFile multipartFile = new MockMultipartFile("image", originalFileName, "image/jpeg",
                new FileInputStream(filePath));

        HomeImageSaveRequest request = new HomeImageSaveRequest(multipartFile, title);
        Long uploadedHomeImageId = homeImageService.createHomeImage(request, admin);

        HomeImage findHomeImage = homeImageService.findById(uploadedHomeImageId);

        String updatedTitle = "title update test";
        HomeImageUpdateRequest updateRequest = new HomeImageUpdateRequest(findHomeImage.getId(), updatedTitle);

        homeImageService.updateHomeImage(updateRequest, admin);

        HomeImage findUpdatedHomeImage = homeImageService.findById(uploadedHomeImageId);

        Assertions.assertThat(findUpdatedHomeImage.getTitle()).isEqualTo(updatedTitle);
    }

}