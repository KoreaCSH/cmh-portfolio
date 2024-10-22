package com.choimyeongheon.portfolio.domain.admin.service;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.admin.domain.SignUpRequest;
import com.choimyeongheon.portfolio.domain.admin.repository.SignUpRequestRepository;
import com.choimyeongheon.portfolio.global.common.DelYn;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.admin.dto.SignUpRequestApiDto;
import com.choimyeongheon.portfolio.web.admin.admin.dto.SignUpRequestDto;
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
public class SignUpRequestService {

    private final SignUpRequestRepository signUpRequestRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUp(SignUpRequestApiDto request) {
        validateDuplicatedUserId(request.getUserId());

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .password(encodedPassword)
                .build();

        SignUpRequest saved = signUpRequestRepository.save(signUpRequest);
        return saved.getId();
    }

    private void validateDuplicatedUserId(String userId) {
        signUpRequestRepository.findByUserId(userId).ifPresent(
                signUpRequest -> {throw new CustomException(ErrorType.DUPLICATED_ADMIN);}
        );
    }

    public List<SignUpRequestDto> findAll() {
        return signUpRequestRepository.findAll()
                .stream()
                .filter(signUpRequest -> signUpRequest.getAcceptYn() == DelYn.N)
                .map(SignUpRequestDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void rejectSignUp(Long id, Admin rejectedBy) {
        SignUpRequest signUpRequest = signUpRequestRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.ADMIN_NOT_FOUND));

        log.info("{} 요청 거부 - 거부자 : {}", signUpRequest.getUserId(), rejectedBy.getUserId());
        signUpRequestRepository.delete(signUpRequest);
    }

}
