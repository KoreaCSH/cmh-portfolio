package com.choimyeongheon.portfolio.domain.contact.domain;

import com.choimyeongheon.portfolio.global.common.BaseEntity;
import com.choimyeongheon.portfolio.global.common.DelYn;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contact extends BaseEntity {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "sender_email")
    private String senderEmail;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "read_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReadYn readYn;

    @Column(name = "del_yn", nullable = false)
    @Enumerated(EnumType.STRING)
    private DelYn delYn;

    // 읽은 시간 추가 ?

    @Builder
    public Contact(String senderName, String senderEmail, String subject, String content) {
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.content = content;
        this.readYn = ReadYn.N;
        this.delYn = DelYn.N;
    }

    public void checkRead() {
        this.readYn = ReadYn.Y;
    }

}
