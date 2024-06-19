package com.choimyeongheon.portfolio.web.visitor.contact.dto;

import com.choimyeongheon.portfolio.domain.contact.domain.Contact;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitorContactRequest {

    @NotBlank(message = "이름을 입력해 주세요.")
    private String senderName;

    @NotBlank(message = "메일을 입력해 주세요.")
    private String senderEmail;

    @NotBlank(message = "제목을 입력해 주세요.")
    private String subject;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    public Contact toEntity() {
        return Contact.builder()
                .senderName(this.senderName)
                .senderEmail(this.senderEmail)
                .subject(this.subject)
                .content(this.content)
                .build();
    }

}
