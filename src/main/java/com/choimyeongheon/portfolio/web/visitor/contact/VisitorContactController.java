package com.choimyeongheon.portfolio.web.visitor.contact;

import com.choimyeongheon.portfolio.domain.contact.service.VisitorContactService;
import com.choimyeongheon.portfolio.web.visitor.contact.dto.VisitorContactRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contact")
public class VisitorContactController {

    private final VisitorContactService senderContactService;

    @GetMapping
    public String contactForm(Model model, VisitorContactRequest request) {
        model.addAttribute("request", request);
        return "contact";
    }

    @PostMapping
    public String contact(@Valid @ModelAttribute("request") VisitorContactRequest request) {
        senderContactService.contact(request);
        return "redirect:/home";
    }

}
