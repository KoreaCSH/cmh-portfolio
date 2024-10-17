package com.choimyeongheon.portfolio.domain.admin.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.admin.repository.AdminRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.admin.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public Long signUp(SignUpRequest request) {

        validateDuplicatedUserId(request.getUserId());

        String encodedPassword = request.getPassword();
        Admin admin = Admin.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .password(encodedPassword)
                .build();

        Admin savedAdmin = adminRepository.save(admin);
        return savedAdmin.getId();
    }

    @Transactional
    public void updateLastLoginDate(Admin admin) {
        Admin findAdmin = adminRepository.findByUserId(admin.getUserId())
                .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));

        findAdmin.updateLastLoginDate();
    }

    private void validateDuplicatedUserId(String userId) {
        adminRepository.findByUserId(userId).ifPresent(
                admin -> {throw new CustomException(ErrorType.DUPLICATED_ADMIN);}
        );
    }

}
