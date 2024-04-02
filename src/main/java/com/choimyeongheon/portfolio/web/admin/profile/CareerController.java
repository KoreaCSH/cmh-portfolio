package com.choimyeongheon.portfolio.web.admin.profile;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.service.CareerService;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileSaveRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/profile/career")
public class CareerController {

    private final CareerService careerService;

    @GetMapping
    public String createForm(ProfileSaveRequest request, Model model) {
        model.addAttribute("request", request);
        return "admin/profile/save";
    }

    @PostMapping
    public String create(@Valid ProfileSaveRequest request,
                         @AuthenticationPrincipal Admin admin) {
        careerService.create(request, admin);

        return "redirect:/admin/home-images";
    }

}
