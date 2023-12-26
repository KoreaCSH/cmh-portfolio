package com.choimyeongheon.portfolio.domain.home.service;

import com.choimyeongheon.portfolio.domain.home.domain.HomeImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class HomeImageServiceTest {

    @Autowired
    HomeImageService homeImageService;

    @Value("${koreaUniv.upload.path}")
    String uploadPath;

    @Test
    void 이미지_저장_성공() {

        String originName = "/Users/csh/test/test.jpg";
        String fileName = originName.substring(originName.lastIndexOf("\\") + 1);
        String path = uploadPath + File.separator + fileName;

        HomeImage image = HomeImage.builder()
                .originName(originName)
                .fileName(fileName)
                .path(path)
                .title("test")
                .build();

    }

}