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

    @GetMapping("/all")
    public String profiles(Model model) {
        List<ProfileResponse> profiles = profileService.findAll();
        List<ProfileType> profileTypes = profileService.findAllProfileType();

        model.addAttribute("profiles", profiles);
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("selectedType", "All");
        return "admin/profile/profiles";
    }

    @GetMapping("/{profileType}")
    public String profilesByType(Model model, @PathVariable(name = "profileType") String profileType) {

        List<ProfileResponse> profiles = profileService.findAllByProfileType(profileType);
        List<ProfileType> profileTypes = profileService.findAllProfileType();

        model.addAttribute("profiles", profiles);
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("selectedType", profileType);
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

    // update 로직 수정해야 한다. - ProfileTypeE 를 찾고, 변경해주는 로직 추가해야 함
    @GetMapping("/update-form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {
        ProfileUpdateRequest request = profileService.findProfileUpdateRequestById(id);
        List<ProfileType> profileTypes = profileService.findAllProfileType();

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

        List<ProfileType> profileTypes = profileService.findAllProfileType();
        request.setProfileDeletionDtoList(profileService.findAllDeletionDto());
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("request", request);
        model.addAttribute("selectedType", "All");
        return "admin/profile/delete";
    }

    @GetMapping("/delete-form/{profileType}")
    public String deleteFormByProfileType(Model model, ProfileDeletionRequest request,
                                          @PathVariable(name = "profileType") String profileType) {

        List<ProfileType> profileTypes = profileService.findAllProfileType();
        request.setProfileDeletionDtoList(profileService.findAllDeletionDtoByProfileType(profileType));
        model.addAttribute("profileTypes", profileTypes);
        model.addAttribute("request", request);
        model.addAttribute("selectedType", profileType);
        return "admin/profile/delete";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("request") ProfileDeletionRequest request) {
        // to-do : DEL_YN 일관성 추가
        profileService.deleteAllByIds(request.getProfileDeletionDtoList());
        return "redirect:/admin/profile/all";
    }

}
