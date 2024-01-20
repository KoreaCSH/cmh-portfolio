package com.choimyeongheon.portfolio.domain.homeImage.repository;

import com.choimyeongheon.portfolio.domain.homeImage.domain.HomeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomeImageRepository extends JpaRepository<HomeImage, Long> {

    @Modifying
    @Query("delete from HomeImage h where h.id in :ids")
    void deleteAllByIds(@Param("ids") List<Long> ids);

    @Query("select h from HomeImage h order by RAND() limit 1")
    List<HomeImage> findRandomly();

}
