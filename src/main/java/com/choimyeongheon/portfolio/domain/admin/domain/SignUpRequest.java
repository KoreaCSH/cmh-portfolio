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
public class SignUpRequest extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "accepted_by")
    private Admin acceptedBy;

    @Column(name = "del_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private DelYn delYn;

    @Builder
    public SignUpRequest(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.delYn = DelYn.N;
    }

    public void updateAcceptedBy(Admin acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

}
