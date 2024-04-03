package com.choimyeongheon.portfolio.domain.profile.domain;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GroupExhibitions extends Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_exhibitions_id")
    private Long id;

    @Builder
    public GroupExhibitions(Integer year, String content, String contentEn, Admin createdBy) {
        super(year, content, contentEn, createdBy);
    }

}
