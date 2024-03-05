package com.choimyeongheon.portfolio.web.home;

import com.choimyeongheon.portfolio.domain.homeImage.service.HomeImageService;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeImageService homeImageService;

    @GetMapping("/home")
    public String home(Model model) {

        HomeImageResponse response = homeImageService.findRandomly();
        model.addAttribute("response", response);

        return "home";
    }

    @GetMapping("/error-alert")
    public String error() {
        return "exception/error";
    }

}
