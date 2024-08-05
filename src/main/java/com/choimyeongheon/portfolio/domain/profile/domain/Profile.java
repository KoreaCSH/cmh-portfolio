package com.choimyeongheon.portfolio.domain.profile.domain;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.global.common.BaseEntity;
import com.choimyeongheon.portfolio.global.common.DelYn;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Slf4j
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

    @ManyToOne
    @JoinColumn(name = "profile_type_id")
    private ProfileType profileType;

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
    public Profile(Integer year, String content, String contentEn, ProfileType profileType, Admin createdBy) {
        this.year = year;
        this.content = content;
        this.contentEn = contentEn;
        this.setProfileType(profileType);
        this.createdBy = createdBy;
        this.delYn = DelYn.N;
    }

    /**
     * 연관관계 편의 메서드 - Profile 생성 시
     * */
    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
        profileType.getProfiles().add(this);
    }

    /**
     * 연관관계 편의 메서드 - Profile 수정 시
     * */
    public void updateProfileTypeE(ProfileType beforeProfileType, ProfileType afterProfileType) {
        beforeProfileType.getProfiles().remove(this);

        this.profileType = afterProfileType;
        profileType.getProfiles().add(this);
    }

    public void update(ProfileUpdateRequest request, ProfileType beforeProfileType, ProfileType afterProfileType, Admin updatedBy) {
        this.year = request.getYear();
        this.content = request.getContent();
        this.contentEn = request.getContentEn();
        this.updateProfileTypeE(beforeProfileType, afterProfileType);
        this.updatedBy = updatedBy;
    }

    public void delete() {
        this.delYn = DelYn.Y;
    }

    public boolean isNotDeleted() {
        return this.delYn == DelYn.N;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Profile profile = (Profile) obj;
        return Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
