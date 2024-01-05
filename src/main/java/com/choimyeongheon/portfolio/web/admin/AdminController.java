package com.choimyeongheon.portfolio.web.admin;

import com.choimyeongheon.portfolio.domain.home.service.HomeImageService;
import com.choimyeongheon.portfolio.web.home.dto.HomeImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final HomeImageService homeImageService;

    @GetMapping("/admin/home")
    public String admin() {
        return "admin/home";
    }

    @GetMapping("/admin/works")
    public String works() {
        return "admin/works";
    }

    @GetMapping("/admin/home/save")
    public String saveForm(Model model, HomeImageRequest request) {
        model.addAttribute("request", request);
        return "admin/save";
    }

    @PostMapping("/admin/home/save")
    public String save(@ModelAttribute("request") HomeImageRequest request) {

        homeImageService.createHomeImage(request);

        return "home";
    }

}
