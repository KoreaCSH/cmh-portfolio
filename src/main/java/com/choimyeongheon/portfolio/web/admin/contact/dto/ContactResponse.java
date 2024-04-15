package com.choimyeongheon.portfolio.web.admin.contact.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ContactResponse {

    private List<ContactDto> contactDtoList;
    private Long totalContactCount;
    private Long notReadContactCount;

}
