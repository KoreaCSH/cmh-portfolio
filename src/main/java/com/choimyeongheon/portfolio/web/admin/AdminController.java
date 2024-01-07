package com.choimyeongheon.portfolio.web.admin;

import com.choimyeongheon.portfolio.domain.home.domain.HomeImage;
import com.choimyeongheon.portfolio.domain.home.service.HomeImageService;
import com.choimyeongheon.portfolio.web.home.dto.HomeImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final HomeImageService homeImageService;

    @GetMapping("/admin/home")
    public String admin(Model model) {

        List<HomeImage> homeImages = homeImageService.findAll();
        model.addAttribute("homeImages", homeImages);

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

        // homeImageResponse 객체 생성 후 변경 - Entity 를 반환하지 않기
        homeImageService.createHomeImage(request);

        return "redirect:/admin/home";
    }

}
