package com.choimyeongheon.portfolio.domain.homeImage.service;

import com.choimyeongheon.portfolio.domain.homeImage.domain.HomeImage;
import com.choimyeongheon.portfolio.domain.homeImage.repository.HomeImageRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageMapper;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageRequest;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeImageService {

    private final HomeImageRepository homeImageRepository;
    private final HomeImageMapper homeImageMapper;

    @Transactional
    public Long createHomeImage(HomeImageRequest request) {
        HomeImage savedImage = homeImageRepository.save(homeImageMapper.toEntity(request));
        return savedImage.getId();
    }

    public HomeImage findById(Long id) {
        return homeImageRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.HOME_IMAGE_NOT_FOUND));
    }

    public List<HomeImage> findAll() {
        return homeImageRepository.findAll();
    }

    public HomeImageResponse findRandomly() {
        HomeImage homeImage = homeImageRepository.findRandomly().get(0);
        return homeImageMapper.toResponse(homeImage);
    }

    @Transactional
    public void deleteAllByIds(List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)) {
            throw new CustomException(ErrorType.EMPTY_DELETION_LIST);
        }

        homeImageRepository.deleteAllByIds(ids);
    }

}
