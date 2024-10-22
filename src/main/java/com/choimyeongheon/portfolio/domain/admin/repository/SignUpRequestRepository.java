package com.choimyeongheon.portfolio.domain.admin.repository;

import com.choimyeongheon.portfolio.domain.admin.domain.SignUpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignUpRequestRepository extends JpaRepository<SignUpRequest, Long> {

    Optional<SignUpRequest> findByUserId(String userId);

}
