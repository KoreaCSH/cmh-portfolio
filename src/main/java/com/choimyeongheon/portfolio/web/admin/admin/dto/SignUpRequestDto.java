package com.choimyeongheon.portfolio.web.admin.admin.dto;

import com.choimyeongheon.portfolio.domain.admin.domain.SignUpRequest;
import com.choimyeongheon.portfolio.global.util.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    private Long id;
    private String userId;
    private String userName;
    private String createdAt;

    public SignUpRequestDto(SignUpRequest request) {
        this.id = request.getId();
        this.userId = request.getUserId();
        this.userName = request.getUserName();
        this.createdAt = DateUtil.yyyyMMddHHmm(request.getCreatedAt());
    }

}
