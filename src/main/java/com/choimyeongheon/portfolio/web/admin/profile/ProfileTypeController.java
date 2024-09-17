package com.choimyeongheon.portfolio.web.admin.profile;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.profile.service.ProfileTypeService;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileTypeDto;
import com.choimyeongheon.portfolio.web.admin.profile.dto.ProfileTypeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/profile-type")
public class ProfileTypeController {

    private final ProfileTypeService profileTypeService;

    @GetMapping
    public String profileTypes(Model model) {
        List<ProfileTypeDto> profileTypes = profileTypeService.findAllDto();
        model.addAttribute("profileTypes", profileTypes);
        return "admin/profiletype/profiletypes";
    }

    @GetMapping("/save-form")
    public String saveForm(ProfileTypeRequest request, Model model) {
        model.addAttribute("request", request);
        return "admin/profiletype/save";
    }

    @PostMapping
    public String save(@ModelAttribute("request") ProfileTypeRequest request,
                       @AuthenticationPrincipal Admin admin) {

        profileTypeService.create(request, admin);
        return "redirect:/admin/profile-type";
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {
        ProfileTypeDto request = profileTypeService.findDto(id);
        model.addAttribute("request", request);
        return "admin/profiletype/update";
    }

    @PutMapping
    public String update(@ModelAttribute("request") ProfileTypeDto request,
                         @AuthenticationPrincipal Admin admin) {

        profileTypeService.update(request, admin);
        return "redirect:/admin/profile-type";
    }

    @GetMapping("/delete-form")
    public String deleteForm(ProfileTypeRequest request, Model model) {
        request.setProfileTypeDtoList(profileTypeService.findAllDto());
        model.addAttribute("request", request);
        return "";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("request") ProfileTypeRequest request,
                         @AuthenticationPrincipal Admin admin) {
        // ProfileType delYn 처리 및 type 에 속한 profile 들도 모두 delYn 처리 - 테스트 필요
        profileTypeService.deleteAll(request, admin);
        return "";
    }

}
