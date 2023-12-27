package com.choimyeongheon.portfolio.web.dto;

import com.choimyeongheon.portfolio.domain.home.domain.HomeImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class HomeImageMapper {

    @Value("${portfolio.upload.path}")
    private String uploadPath;

    public HomeImage toEntity(HomeImageRequest request) {
        String originName = request.getHomeImage().getOriginalFilename();
        String fileName = originName.substring(originName.lastIndexOf("\\") + 1);
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

}
