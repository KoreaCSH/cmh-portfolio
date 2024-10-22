package com.choimyeongheon.portfolio.web.admin.admin.dto;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto {

    private Long id;
    private String userId;
    private String userName;
    private String role;

    public AdminDto(Admin admin) {
        this.id = admin.getId();
        this.userId = admin.getUserId();
        this.userName = admin.getUserName();
        this.role = admin.getRole().name();
    }

}
