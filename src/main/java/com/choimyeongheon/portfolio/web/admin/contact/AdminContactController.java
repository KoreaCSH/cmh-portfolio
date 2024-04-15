package com.choimyeongheon.portfolio.web.admin.contact;

import com.choimyeongheon.portfolio.domain.contact.service.AdminContactService;
import com.choimyeongheon.portfolio.web.admin.contact.dto.ContactDto;
import com.choimyeongheon.portfolio.web.admin.contact.dto.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/contact")
public class AdminContactController {

    private final AdminContactService adminContactService;

    @GetMapping
    public String contacts(Model model) {
        ContactResponse response = adminContactService.findAll();
        model.addAttribute("response", response);
        return "admin/contact/contacts";
    }

    @GetMapping("/{id}")
    public String contactDetail(Model model, @PathVariable("id") Long id) {
        ContactDto contactDto = adminContactService.findById(id);
        model.addAttribute("contact", contactDto);
        return "admin/contact/detail";
    }

}
