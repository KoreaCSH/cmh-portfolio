package com.choimyeongheon.portfolio.domain.admin.domain;

import com.choimyeongheon.portfolio.global.common.DelYn;
import com.choimyeongheon.portfolio.web.admin.admin.dto.AdminDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "accepted_by")
    private String acceptedBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @Column(name = "del_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private DelYn delYn;

    @Column(name = "super_admin_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private DelYn superAdminYn;

    @Builder
    public Admin(String userId, String userName, String password, Admin acceptedBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.acceptedBy = acceptedBy.getUserId();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.role = Role.ADMIN;
        this.delYn = DelYn.N;
        this.superAdminYn = DelYn.N;
    }

    @Override
    public String toString() {
        return this.userId;
    }

    public void updateLastLoginDate() {
        this.lastLoginDate = LocalDateTime.now();
    }

    public void update(AdminDto request, String updatedBy) {
        this.userName = request.getUserName();
        this.role = Role.valueOf(request.getRole());
        this.updatedBy = updatedBy;
    }

}
