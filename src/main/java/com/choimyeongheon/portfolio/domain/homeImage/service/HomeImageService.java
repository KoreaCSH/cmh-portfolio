package com.choimyeongheon.portfolio.domain.homeImage.service;

import com.choimyeongheon.portfolio.domain.homeImage.domain.HomeImage;
import com.choimyeongheon.portfolio.domain.homeImage.repository.HomeImageRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeImageService {

    private final HomeImageRepository homeImageRepository;
    private final HomeImageMapper homeImageMapper;

    @Transactional
    public Long createHomeImage(HomeImageSaveRequest request) {
        HomeImage savedImage = homeImageRepository.save(homeImageMapper.toEntity(request));
        return savedImage.getId();
    }

    @Transactional
    public void updateHomeImage(HomeImageUpdateRequest request) {
        HomeImage homeImage = findById(request.getId());
        homeImage.updateTitle(request.getUpdatedTitle());
    }

    @Transactional
    public void deleteAllByIds(List<HomeImageDeletionDto> deletedHomeImageList) {

        List<Long> deletedHomeImageIdList = deletedHomeImageList
                                                .stream()
                                                .filter(HomeImageDeletionDto::getIsDeleted)
                                                .map(HomeImageDeletionDto::getId)
                                                .collect(Collectors.toList());

        if(CollectionUtils.isEmpty(deletedHomeImageIdList)) {
            throw new CustomException(ErrorType.EMPTY_DELETION_LIST);
        }

        homeImageRepository.deleteAllByIds(deletedHomeImageIdList);
    }

    public HomeImage findById(Long id) {
        return homeImageRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.HOME_IMAGE_NOT_FOUND));
    }

    public List<HomeImageResponse> findAll() {
        return homeImageRepository.findAll()
                                    .stream()
                                    .map(homeImage -> homeImageMapper.toResponse(homeImage))
                                    .collect(Collectors.toList());
    }

    public List<HomeImageDeletionDto> findAllDeletionDto() {
        return homeImageRepository.findAll()
                        .stream()
                        .map(homeImage -> new HomeImageDeletionDto(homeImage.getId(), homeImage.getFileName(), homeImage.getTitle()))
                        .collect(Collectors.toList());
    }

    public HomeImageResponse findRandomly() {
        List<HomeImage> randomHomeImage = homeImageRepository.findRandomly();
        if (CollectionUtils.isEmpty(randomHomeImage)) {
            return new HomeImageResponse(0L, "", "등록한 이미지가 존재하지 않습니다.");
        }

        return homeImageMapper.toResponse(randomHomeImage.get(0));
    }

    public ResponseEntity<Resource> display(String fileName) {
        return homeImageMapper.toResource(fileName);
    }

}
