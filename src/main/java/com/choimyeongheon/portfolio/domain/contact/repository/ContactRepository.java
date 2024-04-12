package com.choimyeongheon.portfolio.domain.contact.repository;

import com.choimyeongheon.portfolio.domain.contact.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
