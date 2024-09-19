package com.choimyeongheon.portfolio.domain.profile.repository;

import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileTypeRepository extends JpaRepository<ProfileType, Long> {

    List<ProfileType> findAllByOrderByPriority();

}
