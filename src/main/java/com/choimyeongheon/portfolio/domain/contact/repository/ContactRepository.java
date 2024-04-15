package com.choimyeongheon.portfolio.domain.contact.repository;

import com.choimyeongheon.portfolio.domain.contact.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT COUNT(c.id) FROM Contact c WHERE c.readYn = 'N'")
    Long countAllNotRead();

    List<Contact> findAllByOrderByCreatedAtDesc();

}
