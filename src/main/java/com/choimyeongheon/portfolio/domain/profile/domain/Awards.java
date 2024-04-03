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
public class Awards extends Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "awards_id")
    private Long id;

    @Builder
    public Awards(Integer year, String content, String contentEn, Admin createdBy) {
        super(year, content, contentEn, createdBy);
    }

}
