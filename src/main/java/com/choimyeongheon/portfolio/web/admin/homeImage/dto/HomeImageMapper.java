package com.choimyeongheon.portfolio.web.admin.homeImage.dto;

import com.choimyeongheon.portfolio.domain.homeImage.domain.HomeImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class HomeImageMapper {

    @Value("${portfolio.upload.path}")
    private String uploadPath;

    public HomeImage toEntity(HomeImageRequest request) {
        String originName = request.getHomeImage().getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originName.substring(originName.lastIndexOf("\\") + 1);
        String path = uploadPath + File.separator + fileName;
        Path savePath = Paths.get(path);
        try {
            request.getHomeImage().transferTo(savePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return HomeImage.builder()
                .originName(originName)
                .fileName(fileName)
                .path(path)
                .title(request.getTitle())
                .build();
    }

    public HomeImageResponse toResponse(HomeImage homeImage) {
        return new HomeImageResponse(homeImage.getFileName(), homeImage.getTitle());
    }

    public ResponseEntity<Resource> toResource(String fileName) throws IOException {

        // Resource 가 무엇인지
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        Path filePath = Paths.get(uploadPath + File.separator + fileName);

        // Exception 대응 추가
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", Files.probeContentType(filePath));

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

}
