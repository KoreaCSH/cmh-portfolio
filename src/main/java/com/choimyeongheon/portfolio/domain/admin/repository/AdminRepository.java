package com.choimyeongheon.portfolio.domain.admin.repository;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
