package com.choimyeongheon.portfolio.domain.homeImage.service;

import com.choimyeongheon.portfolio.domain.homeImage.domain.HomeImage;
import com.choimyeongheon.portfolio.domain.homeImage.repository.HomeImageRepository;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageMapper;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 삭제 로직 추가

    public HomeImage findById(Long id) {
        return homeImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    public List<HomeImage> findAll() {
        return homeImageRepository.findAll();
    }

    @Transactional
    public void deleteAllByIds(List<Long> ids) {
        homeImageRepository.deleteAllByIds(ids);
    }

}
