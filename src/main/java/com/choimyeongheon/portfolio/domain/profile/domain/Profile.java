package com.choimyeongheon.portfolio.domain.profile.domain;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.global.common.BaseEntity;
import com.choimyeongheon.portfolio.global.common.DelYn;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileUpdateRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Profile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "content")
    private String content;

    @Column(name = "content_en")
    private String contentEn;

    @Column(name = "profile_type")
    @Enumerated(EnumType.STRING)
    private ProfileType profileType;

    @ManyToOne
    @JoinColumn(name = "profile_type_id")
    private ProfileTypeE profileTypeE;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Admin createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Admin updatedBy;

    @Column(name = "del_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private DelYn delYn;

    // V1 - ASIS - 삭제 필요
    @Builder
    public Profile(Integer year, String content, String contentEn, ProfileType profileType, Admin createdBy) {
        this.year = year;
        this.content = content;
        this.contentEn = contentEn;
        this.profileType = profileType;
        this.createdBy = createdBy;
        this.delYn = DelYn.N;
    }


    // V2 - TOBE - 테스트 필요
    @Builder
    public Profile(Integer year, String content, String contentEn, ProfileTypeE profileTypeE, Admin createdBy) {
        this.setProfileTypeE(profileTypeE);
        this.year = year;
        this.content = content;
        this.contentEn = contentEn;
        this.createdBy = createdBy;
        this.delYn = DelYn.N;
    }

    // 연관관계 편의 메서드
    public void setProfileTypeE(ProfileTypeE profileTypeE) {
        this.profileTypeE = profileTypeE;
        profileTypeE.getProfiles().add(this);
    }

    public void update(ProfileUpdateRequest request, Admin updatedBy) {
        this.year = request.getYear();
        this.content = request.getContent();
        this.contentEn = request.getContentEn();
        this.profileType = ProfileType.from(request.getProfileType());
        this.updatedBy = updatedBy;
    }

    public void delete() {
        this.delYn = DelYn.Y;
    }

    public boolean isNotDeleted() {
        return this.delYn == DelYn.N;
    }

}
