package com.choimyeongheon.portfolio.domain.admin.domain;

import com.choimyeongheon.portfolio.global.common.BaseEntity;
import com.choimyeongheon.portfolio.global.common.DelYn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

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
    public Admin(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = Role.ADMIN;
        this.delYn = DelYn.N;
    }

}
