package com.choimyeongheon.portfolio.domain.profile.domain;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.global.common.BaseEntity;
import com.choimyeongheon.portfolio.global.common.DelYn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProfileTypeE extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_type_id")
    private Long id;

    private String type;

    // profileType 으로 수정 필요
    // 수정, 삭제 시 Profile 에도 영향 주도록 설정
    @OneToMany(mappedBy = "profileTypeE", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profile> profiles = new ArrayList<>();

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
    public ProfileTypeE(String type, Admin createdBy) {
        this.type = type;
        this.createdBy = createdBy;
        this.delYn = DelYn.N;
    }

    public void update(String type, Admin updatedBy) {
        this.type = type;
        this.updatedBy = updatedBy;
    }

    public void delete(Admin updatedBy) {
        this.profiles.stream().forEach(Profile::delete);
        this.delYn = DelYn.Y;
        this.updatedBy = updatedBy;
    }

}