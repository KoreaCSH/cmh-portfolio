package com.choimyeongheon.portfolio.domain.contact.service;

import com.choimyeongheon.portfolio.domain.contact.domain.Contact;
import com.choimyeongheon.portfolio.domain.contact.domain.ReadYn;
import com.choimyeongheon.portfolio.domain.contact.repository.ContactRepository;
import com.choimyeongheon.portfolio.global.exception.CustomException;
import com.choimyeongheon.portfolio.global.exception.ErrorType;
import com.choimyeongheon.portfolio.web.admin.contact.dto.ContactDto;
import com.choimyeongheon.portfolio.web.admin.contact.dto.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminContactService {

    private final ContactRepository contactRepository;

    public ContactResponse findAll() {
        List<ContactDto> contactDtoList = contactRepository.findAllByOrderByCreatedAtDesc()
                                                .stream()
                                                .map(ContactDto::new)
                                                .collect(Collectors.toList());

        Long totalContactCount = Long.valueOf(contactDtoList.size());
        Long notReadContactCount = contactRepository.countAllNotRead();

        return new ContactResponse(contactDtoList, totalContactCount, notReadContactCount);
    }

    @Transactional
    public ContactDto findById(Long id) {
        Contact findContact = contactRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.CONTACT_NOT_FOUND));

        if (findContact.getReadYn() == ReadYn.N) {
            findContact.checkRead();
        }

        return new ContactDto(findContact);
    }

}
