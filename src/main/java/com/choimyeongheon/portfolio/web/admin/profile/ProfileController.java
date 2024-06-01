package com.choimyeongheon.portfolio.web.admin.profile;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.domain.ProfileType;
import com.choimyeongheon.portfolio.domain.profile.service.ProfileService;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileDeletionRequest;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileResponse;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileSaveRequest;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/all")
    public String profiles(Model model, ProfileUpdateRequest request) {
        List<ProfileResponse> profiles = profileService.findAll();
        List<ProfileType> profileTypes = profileService.findAllProfileType();

        model.addAttribute("profiles", profiles);
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("selectedType", "All");
        model.addAttribute("request", request);
        return "admin/profile/profiles";
    }

    @GetMapping("/{profileType}")
    public String profilesByType(Model model, ProfileUpdateRequest request,
                                @PathVariable(name = "profileType") String profileType) {

        List<ProfileResponse> profiles = profileService.findAllByProfileType(profileType);
        List<ProfileType> profileTypes = profileService.findAllProfileType();

        model.addAttribute("profiles", profiles);
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("selectedType", profileType);
        model.addAttribute("request", request);
        return "admin/profile/profiles";
    }

    @GetMapping("/save-form")
    public String createForm(Model model, ProfileSaveRequest request) {
        List<ProfileType> profileTypes = profileService.findAllProfileType();

        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("request", request);
        return "admin/profile/save";
    }

    @PostMapping
    public String create(@ModelAttribute("request") @Valid ProfileSaveRequest request,
                         @AuthenticationPrincipal Admin admin) {
        profileService.create(request, admin);

        return "redirect:/admin/profile/all";
    }

    @PutMapping
    public String update(@ModelAttribute("request") @Valid ProfileUpdateRequest request,
                         @AuthenticationPrincipal Admin admin) {
        profileService.update(request, admin);

        return "redirect:/admin/profile";
    }

    @GetMapping("delete-form")
    public String deleteForm(Model model, ProfileDeletionRequest request) {

        List<ProfileType> profileTypes = profileService.findAllProfileType();
        request.setProfileDeletionDtoList(profileService.findAllDeletionDto());
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("request", request);
        return "admin/profile/delete";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("request") ProfileDeletionRequest request) {
        profileService.deleteAllByIds(request.getProfileDeletionDtoList());
        return "redirect:/admin/profile";
    }

}
