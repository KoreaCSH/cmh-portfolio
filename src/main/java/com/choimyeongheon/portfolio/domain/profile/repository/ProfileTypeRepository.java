package com.choimyeongheon.portfolio.domain.profile.repository;

import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileTypeRepository extends JpaRepository<ProfileType, Long> {
}
