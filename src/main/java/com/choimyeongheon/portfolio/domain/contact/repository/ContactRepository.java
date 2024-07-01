package com.choimyeongheon.portfolio.domain.contact.repository;

import com.choimyeongheon.portfolio.domain.contact.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Modifying
    @Query("UPDATE Contact c SET c.delYn = 'Y' WHERE c.id in :ids")
    void deleteAllByIds(@Param("ids") List<Long> ids);

    @Query("SELECT COUNT(c.id) FROM Contact c WHERE c.readYn = 'N' AND c.delYn = 'N'")
    Long countAllNotRead();

    List<Contact> findAllByOrderByCreatedAtDesc();

}
