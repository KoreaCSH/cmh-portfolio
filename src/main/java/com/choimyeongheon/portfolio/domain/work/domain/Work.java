package com.choimyeongheon.portfolio.domain.work.domain;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Work extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Long id;

    // 이미지 파일 저장에 필요한 필드
    private String originName;
    private String fileName;
    private String path;

    // 이미지 파일의 정보
    private String title;

    @Temporal(TemporalType.DATE)
    private LocalDate workDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    @Column(name = "del_yn", nullable = false)
    private Character delYn;

    @Builder
    public Work(String originName, String fileName, String path, String title, LocalDate workDate, Admin createdBy) {
        this.originName = originName;
        this.fileName = fileName;
        this.path = path;
        this.title = title;
        this.workDate = workDate;
        this.createdBy = createdBy;
        this.delYn = 'N';
    }

    public void updateTitleAndWorkDate(String title, LocalDate workDate, Admin updatedBy) {
        this.title = title;
        this.workDate = workDate;
        this.updatedBy = updatedBy;
    }

}
