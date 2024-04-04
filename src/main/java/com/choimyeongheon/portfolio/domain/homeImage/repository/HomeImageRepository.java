package com.choimyeongheon.portfolio.domain.homeImage.repository;

import com.choimyeongheon.portfolio.domain.homeImage.domain.HomeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HomeImageRepository extends JpaRepository<HomeImage, Long> {

    @Modifying
    @Query("update HomeImage h set h.delYn = 'Y' where h.id in :ids")
    void deleteAllByIds(@Param("ids") List<Long> ids);

    @Query("select h from HomeImage h where h.delYn = 'N' order by RAND() limit 1")
    List<HomeImage> findRandomly();

    @Query("select h from HomeImage h where h.delYn = 'N' and h.id = :id")
    Optional<HomeImage> findNotDeletedById(@Param("id") Long id);

}
