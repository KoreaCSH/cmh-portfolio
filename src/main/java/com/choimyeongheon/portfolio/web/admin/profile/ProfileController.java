package com.choimyeongheon.portfolio.web.admin.profile;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.service.ProfileService;
import com.choimyeongheon.portfolio.domain.profile.service.ProfileTypeService;
import com.choimyeongheon.portfolio.web.admin.profile.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/profile")
@Slf4j
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileTypeService profileTypeService;

    @GetMapping("/all")
    public String profiles(Model model) {
        List<ProfileResponse> profiles = profileService.findAll();
        List<ProfileTypeDto> profileTypes = profileTypeService.findAllDto();

        model.addAttribute("profiles", profiles);
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("selectedType", "All");
        return "admin/profile/profiles";
    }

    @GetMapping("/{profileType}")
    public String profilesByType(Model model, @PathVariable(name = "profileType") Long profileTypeId) {

        List<ProfileResponse> profiles = profileService.findAllByProfileTypeId(profileTypeId);
        List<ProfileTypeDto> profileTypes = profileTypeService.findAllDto();

        model.addAttribute("profiles", profiles);
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("selectedType", profileTypeId);
        return "admin/profile/profiles";
    }

    @GetMapping("/save-form")
    public String createForm(Model model, ProfileSaveRequest request) {
        List<ProfileTypeDto> profileTypes = profileTypeService.findAllDto();

        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("request", request);
        return "admin/profile/save";
    }

    @PostMapping("/normal")
    public String create(@ModelAttribute("request") @Valid ProfileSaveRequest request,
                         @AuthenticationPrincipal Admin admin) {
        profileService.create(request, admin);

        return "redirect:/admin/profile/all";
    }

    @GetMapping("/from-to/save-form")
    public String createFromToForm(Model model, ProfileFromToSaveRequest request) {
        List<ProfileTypeDto> profileTypes = profileTypeService.findAllDto();

        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("request", request);
        return "admin/profile/from-to-save";
    }

    @PostMapping("/from-to")
    public String createFromTo(@ModelAttribute("request") @Valid ProfileFromToSaveRequest request,
                               @AuthenticationPrincipal Admin admin) {
        profileService.createFromTo(request, admin);

        return "redirect:/admin/profile/all";
    }

    // update 로직 수정해야 한다. - ProfileType 를 찾고, 변경해주는 로직 추가해야 함
    @GetMapping("/update-form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {
        ProfileUpdateRequest request = profileService.findProfileUpdateRequestById(id);
        List<ProfileTypeDto> profileTypes = profileTypeService.findAllDto();

        model.addAttribute("request", request);
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("selectedType", request.getProfileType());
        log.info("selectedType : " + request.getProfileType());
        return "admin/profile/update";
    }

    @PutMapping
    public String update(@ModelAttribute("request") @Valid ProfileUpdateRequest request,
                         @AuthenticationPrincipal Admin admin) {
        profileService.update(request, admin);
        return "redirect:/admin/profile/all";
    }

    @GetMapping("/delete-form/all")
    public String deleteForm(Model model, ProfileDeletionRequest request) {

        List<ProfileTypeDto> profileTypes = profileTypeService.findAllDto();
        request.setProfileDeletionDtoList(profileService.findAllDeletionDto());
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("request", request);
        model.addAttribute("selectedType", "All");
        return "admin/profile/delete";
    }

    @GetMapping("/delete-form/{profileType}")
    public String deleteFormByProfileType(Model model, ProfileDeletionRequest request,
                                          @PathVariable(name = "profileType") Long profileTypeId) {

        List<ProfileTypeDto> profileTypes = profileTypeService.findAllDto();
        request.setProfileDeletionDtoList(profileService.findAllDeletionDtoByProfileTypeId(profileTypeId));
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("request", request);
        model.addAttribute("selectedType", profileTypeId);
        return "admin/profile/delete";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("request") ProfileDeletionRequest request,
                         @AuthenticationPrincipal Admin admin) {
        // to-do : DEL_YN 일관성 추가
        profileService.deleteAllByIds(request.getProfileDeletionDtoList(), admin);
        return "redirect:/admin/profile/all";
    }

}
