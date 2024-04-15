package com.choimyeongheon.portfolio.web.admin.contact.dto;

import com.choimyeongheon.portfolio.domain.contact.domain.Contact;
import com.choimyeongheon.portfolio.domain.contact.domain.ReadYn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ContactDto {

    private Long id;
    private String senderName;
    private String senderEmail;
    private String subject;
    private String content;
    private String createdAt;
    private String readYn;

    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.senderName = contact.getSenderName();
        this.senderEmail = contact.getSenderEmail();
        this.subject = contact.getSubject();
        this.content = contact.getContent();
        // 연-월-일 시-분 까지만
        this.createdAt = contact.getCreatedAt().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
        this.readYn = contact.getReadYn().name();
    }

}
