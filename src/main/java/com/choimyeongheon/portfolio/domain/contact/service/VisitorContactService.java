package com.choimyeongheon.portfolio.domain.contact.service;

import com.choimyeongheon.portfolio.domain.contact.domain.Contact;
import com.choimyeongheon.portfolio.domain.contact.repository.ContactRepository;
import com.choimyeongheon.portfolio.web.visitor.contact.dto.VisitorContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitorContactService {

    private final ContactRepository contactRepository;

    @Transactional
    public void contact(VisitorContactRequest request) {
        Contact contact = request.toEntity();
        contactRepository.save(contact);
    }

}
