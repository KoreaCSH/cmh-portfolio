package com.choimyeongheon.portfolio.web.admin.contact.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactDeleteRequest {

    List<ContactDto> contactDtoList;

}
