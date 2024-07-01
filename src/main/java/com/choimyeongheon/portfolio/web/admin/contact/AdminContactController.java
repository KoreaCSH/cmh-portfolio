package com.choimyeongheon.portfolio.web.admin.contact;

import com.choimyeongheon.portfolio.domain.contact.service.AdminContactService;
import com.choimyeongheon.portfolio.web.admin.contact.dto.ContactDeleteRequest;
import com.choimyeongheon.portfolio.web.admin.contact.dto.ContactDto;
import com.choimyeongheon.portfolio.web.admin.contact.dto.ContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/contact")
public class AdminContactController {

    private final AdminContactService adminContactService;

    @GetMapping
    public String contacts(Model model) {
        ContactResponse response = adminContactService.findAll();
        model.addAttribute("response", response);
        model.addAttribute("request", new ContactDeleteRequest(response.getContactDtoList()));
        return "admin/contact/contacts";
    }

    @GetMapping("/{id}")
    public String contactDetail(Model model, @PathVariable("id") Long id) {
        ContactDto contactDto = adminContactService.findById(id);
        model.addAttribute("contact", contactDto);
        return "admin/contact/detail";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("request") ContactDeleteRequest request) {
        adminContactService.deleteAllByIds(request.getContactDtoList());
        return "redirect:/admin/contact";
    }

}
