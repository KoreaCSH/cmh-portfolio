package com.choimyeongheon.portfolio.web.admin.profile;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.service.ProfileService;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileResponse;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileSaveRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public String profiles(Model model) {
        List<ProfileResponse> profiles = profileService.findAll();
        List<String> profileTypes = profileService.findAllProfileType();

        model.addAttribute("profiles", profiles);
        model.addAttribute("profileTypes", profileTypes);
        return "admin/profile/profiles";
    }

    @GetMapping("/save-form")
    public String createForm(ProfileSaveRequest request, Model model) {
        List<String> profileTypes = profileService.findAllProfileType();

        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("request", request);
        return "admin/profile/save";
    }

    @PostMapping
    public String create(@Valid ProfileSaveRequest request,
                         @AuthenticationPrincipal Admin admin) {
        profileService.create(request, admin);

        return "redirect:/admin/profile";
    }

}
