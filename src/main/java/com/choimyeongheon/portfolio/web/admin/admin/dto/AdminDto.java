package com.choimyeongheon.portfolio.web.admin.admin.dto;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.global.util.DateUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto {

    private Long id;
    private String userId;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String userName;
    private String createdAt;
    private String role;

    public AdminDto(Admin admin) {
        this.id = admin.getId();
        this.userId = admin.getUserId();
        this.userName = admin.getUserName();
        this.createdAt = DateUtil.yyyyMMddHHmm(admin.getCreatedAt());
        this.role = admin.getRole().name();
    }

}
