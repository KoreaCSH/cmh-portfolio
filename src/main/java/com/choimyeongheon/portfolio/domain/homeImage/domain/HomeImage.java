package com.choimyeongheon.portfolio.domain.homeImage.domain;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.global.common.BaseEntity;
import com.choimyeongheon.portfolio.global.common.DelYn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@DynamicUpdate // JPA 스펙은 아니다. 조금 더 고려하자.
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

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    @Column(name = "del_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private DelYn delYn;

    @Builder
    public HomeImage(String originName, String fileName, String path, String title, Admin createdBy) {
        this.originName = originName;
        this.fileName = fileName;
        this.path = path;
        this.title = title;
        this.createdBy = createdBy;
        this.delYn = DelYn.N;
    }

    public void updateTitle(String title, Admin updatedBy) {
        // null 체크
        this.title = title;
        this.updatedBy = updatedBy;
    }

}
