package com.choimyeongheon.portfolio.domain.admin.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.admin.repository.AdminRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.admin.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(SignUpRequest request) {

        validateDuplicatedUserId(request.getUserId());

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Admin admin = Admin.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .password(encodedPassword)
                .build();

        Admin savedAdmin = adminRepository.save(admin);
        return savedAdmin.getId();
    }

    private void validateDuplicatedUserId(String userId) {
        adminRepository.findByUserId(userId).ifPresent(
                admin -> {throw new CustomException(ErrorType.DUPLICATED_ADMIN);}
        );
    }

}
