package com.choimyeongheon.portfolio.domain.admin.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.admin.domain.SignUpRequest;
import com.choimyeongheon.portfolio.domain.admin.repository.AdminRepository;
import com.choimyeongheon.portfolio.domain.admin.repository.SignUpRequestRepository;
import com.choimyeongheon.portfolio.global.common.DelYn;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.admin.dto.AdminDto;
import com.choimyeongheon.portfolio.web.admin.admin.dto.PasswordUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final SignUpRequestRepository signUpRequestRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long permitSignUp(Long id, Admin acceptedBy) {
        SignUpRequest request = findSignUpRequestById(id);
        validateDuplicatedUserId(request.getUserId());

        Admin admin = Admin.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .password(request.getPassword())
                .acceptedBy(acceptedBy)
                .build();

        Admin savedAdmin = adminRepository.save(admin);
        request.updateAcceptedByAndAcceptYn(acceptedBy);
        return savedAdmin.getId();
    }

    @Transactional
    public void update(AdminDto request, Admin updatedBy) {
        Admin admin = adminRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));

        admin.update(request, updatedBy.getUserId());
    }

    @Transactional
    public void updateLastLoginDate(Admin admin) {
        Admin findAdmin = adminRepository.findByUserId(admin.getUserId())
                .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));

        findAdmin.updateLastLoginDate();
    }

    @Transactional
    public void withdraw(Long id, Admin admin) {
        Admin findAdmin = adminRepository.findById(id)
                            .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));

        SignUpRequest findSignUpRequest = signUpRequestRepository.findByUserId(findAdmin.getUserId())
                                            .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));

        validateSuperAdminYn(findAdmin, admin);
        log.info("{} 탈퇴 처리 - 요청인 : {}", findAdmin.getUserId(), admin.getUserId());
        adminRepository.delete(findAdmin);
        signUpRequestRepository.delete(findSignUpRequest);
    }

    @Transactional
    public void updatePassword(PasswordUpdateRequest request) {
        Admin findAdmin = adminRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));

        validatePasswordUpdate(findAdmin.getPassword(), request.getPrevPassword());

        findAdmin.updatePassword(passwordEncoder.encode(request.getNewPassword()));
    }

    public AdminDto findDtoById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));

        return new AdminDto(admin);
    }

    public List<AdminDto> findAll() {
        return adminRepository.findAll()
                    .stream()
                    .filter(admin -> admin.getDelYn() == DelYn.N)
                    .map(AdminDto::new)
                    .collect(Collectors.toList());
    }

    public PasswordUpdateRequest getPasswordUpdateRequest(String userId) {
        Admin admin = adminRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));

        return new PasswordUpdateRequest(admin.getId());
    }

    private void validateDuplicatedUserId(String userId) {
        adminRepository.findByUserId(userId).ifPresent(
                admin -> {throw new CustomException(ErrorType.DUPLICATED_ADMIN);}
        );
    }

    private SignUpRequest findSignUpRequestById(Long id) {
        return signUpRequestRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));
    }

    private void validateSuperAdminYn(Admin findAdmin, Admin admin) {
        if (admin.getSuperAdminYn() == DelYn.N) {
            throw new CustomException(ErrorType.WITHDRAW_FORBIDDEN);
        }
        if (findAdmin.getSuperAdminYn() == DelYn.Y) {
            throw new CustomException(ErrorType.WITHDRAW_IMPOSSIBLE);
        }
    }

    private void validatePasswordUpdate(String prevPassword, String inputPrevPassword) {
        if (!passwordEncoder.matches(inputPrevPassword, prevPassword)) {
            throw new CustomException(ErrorType.PREV_PASSWORD_NOT_MATCHED);
        }
    }

}
