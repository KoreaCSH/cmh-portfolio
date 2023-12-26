package com.choimyeongheon.portfolio.domain.home.domain;

import com.choimyeongheon.portfolio.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomeImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "home_image_id")
    private Long id;

    // 이미지 파일 저장에 필요한 필드
    private String originName;
    private String fileName;
    private String path;

    // 이미지 파일의 정보
    private String title;

    @Builder
    public HomeImage(String originName, String fileName, String path, String title) {
        this.originName = originName;
        this.fileName = fileName;
        this.path = path;
        this.title = title;
    }

}
