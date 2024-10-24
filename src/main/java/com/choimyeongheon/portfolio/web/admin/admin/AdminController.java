package com.choimyeongheon.portfolio.web.admin.admin;

import com.choimyeongheon.portfolio.domain.admin.domain.Admin;
import com.choimyeongheon.portfolio.domain.admin.domain.Role;
import com.choimyeongheon.portfolio.domain.admin.service.AdminService;
import com.choimyeongheon.portfolio.domain.admin.service.SignUpRequestService;
import com.choimyeongheon.portfolio.web.admin.admin.dto.AdminDto;
import com.choimyeongheon.portfolio.web.admin.admin.dto.SignUpRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class AdminController {

    private final SignUpRequestService signUpRequestService;
    private final AdminService adminService;

    @GetMapping
    public String userList(Model model) {
        List<AdminDto> userList = adminService.findAll();
        model.addAttribute("userList", userList);
        return "admin/user/user-list";
    }

    @GetMapping("/request-list")
    public String signUpRequestList(Model model) {
        List<SignUpRequestDto> signUpRequestDtoList = signUpRequestService.findAll();
        model.addAttribute("signUpRequestList", signUpRequestDtoList);
        return "admin/user/request-list";
    }

    @GetMapping("/update-form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") Long id) {
        AdminDto request = adminService.findDtoById(id);
        model.addAttribute("request", request);
        model.addAttribute("roles", Role.getNames());
        return "admin/user/update";
    }

    @PutMapping("/update")
    public String update(@ModelAttribute("request") @Valid AdminDto request,
                         @AuthenticationPrincipal Admin admin) {
        adminService.update(request, admin);
        return "redirect:/admin/user";
    }

    @PostMapping("/permit")
    public String permitSignUp(@RequestParam("id") Long id, @AuthenticationPrincipal Admin admin) {
        adminService.permitSignUp(id, admin);
        return "redirect:/admin/user";
    }

    @DeleteMapping("/reject")
    public String rejectSignUp(@RequestParam("id") Long id, @AuthenticationPrincipal Admin admin) {
        signUpRequestService.rejectSignUp(id, admin);
        return "redirect:/admin/user";
    }

    @DeleteMapping("/withdraw")
    public String withdraw(@RequestParam("id") Long id, @AuthenticationPrincipal Admin admin) {
        adminService.withdraw(id, admin);
        return "redirect:/admin/user";
    }

}
