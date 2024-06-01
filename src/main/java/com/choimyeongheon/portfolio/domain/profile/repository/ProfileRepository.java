package com.choimyeongheon.portfolio.domain.profile.repository;

import com.choimyeongheon.portfolio.domain.profile.domain.Profile;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.delYn = 'N' ORDER BY p.profileType, p.year")
    List<Profile> findAllOrderByYear();

    @Query("SELECT p FROM Profile p WHERE p.profileType = :profileType AND p.delYn = 'N' ORDER BY p.profileType, p.year")
    List<Profile> findAllByProfileTypeOrderByYear(@Param("profileType") ProfileType profileType);

    @Modifying
    @Query("UPDATE Profile p SET p.delYn = 'Y' WHERE p.id IN :ids")
    void deleteAllByIds(@Param("ids") List<Long> ids);

}
