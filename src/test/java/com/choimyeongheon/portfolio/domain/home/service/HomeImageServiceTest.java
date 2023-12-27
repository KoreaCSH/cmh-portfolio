package com.choimyeongheon.portfolio.domain.home.service;

import com.choimyeongheon.portfolio.domain.home.domain.HomeImage;
import com.choimyeongheon.portfolio.web.dto.HomeImageRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HomeImageServiceTest {

    @Autowired
    HomeImageService homeImageService;

    @Value("${portfolio.upload.path}")
    String uploadPath;

    @Test
    // @Rollback(value = true)
    void 이미지_저장_성공() throws IOException {

        String originalFileName = "test1.jpeg";
        String filePath = "src/main/resources/static/img/test1.jpeg";
        String title = "title test";
        MultipartFile multipartFile = new MockMultipartFile("image", originalFileName, "image/jpeg",
                                                            new FileInputStream(filePath));

        HomeImageRequest request = new HomeImageRequest(multipartFile, title);
        Long uploadedHomeImageId = homeImageService.createHomeImage(request);

        HomeImage findHomeImage = homeImageService.findById(uploadedHomeImageId);

        Assertions.assertThat(findHomeImage.getPath()).isEqualTo(uploadPath + "/" + originalFileName);
    }

}