package com.choimyeongheon.portfolio.domain.home.service;

import com.choimyeongheon.portfolio.domain.home.domain.HomeImage;
import com.choimyeongheon.portfolio.domain.home.repository.HomeImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeImageService {

    private final HomeImageRepository homeImageRepository;

    @Value("${portfolio.upload.path}")
    private String uploadPath;

    @Transactional
    public Long createHomeImage(HomeImage homeImage) {
        HomeImage savedImage = homeImageRepository.save(homeImage);
        return savedImage.getId();
    }

}
