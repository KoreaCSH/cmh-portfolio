package com.choimyeongheon.portfolio.domain.profile.repository;

import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.delYn = 'N' ORDER BY p.profileType, p.year")
    List<Profile> findAllOrderByYear();

}
