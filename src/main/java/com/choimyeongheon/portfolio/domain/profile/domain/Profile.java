package com.choimyeongheon.portfolio.domain.profile.domain;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Profile extends BaseEntity {

    @Column(name = "year")
    private Integer year;

    @Column(name = "content")
    private String content;

    @Column(name = "content_en")
    private String contentEn;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    public Profile(Integer year, String content, String contentEn, Admin createdBy) {
        this.year = year;
        this.content = content;
        this.contentEn = contentEn;
        this.createdBy = createdBy;
    }

    public void update(Integer year, String content, String contentEn, Admin updatedBy) {
        this.year = year;
        this.content = content;
        this.contentEn = contentEn;
        this.updatedBy = updatedBy;
    }

}
