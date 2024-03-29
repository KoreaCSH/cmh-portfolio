package com.choimyeongheon.portfolio.domain.admin.repository;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUserId(String userId);

}
