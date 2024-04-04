package com.choimyeongheon.portfolio.domain.work.repository;

import com.choimyeongheon.portfolio.domain.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Long> {

    List<Work> findAllByOrderByWorkDateDesc();

    @Query("SELECT w FROM Work w WHERE YEAR(w.workDate) = :findYear ORDER BY w.workDate DESC")
    List<Work> findByYearOrderByWorkDateDesc(@Param("findYear") int findYear);

    @Modifying
    @Query("UPDATE Work w SET w.delYn = 'Y' WHERE w.id in :ids")
    void deleteAllByIds(@Param("ids") List<Long> ids);

    @Query("SELECT w FROM Work w WHERE w.delYn = 'N' AND w.id = :id")
    Optional<Work> findNotDeletedById(@Param("id") Long id);

}
