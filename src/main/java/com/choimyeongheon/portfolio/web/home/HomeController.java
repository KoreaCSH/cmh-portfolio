package com.choimyeongheon.portfolio.web.home;

import com.choimyeongheon.portfolio.domain.homeImage.service.HomeImageService;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String error(@RequestParam(name = "errorMessage", required = false) String errorMessage, Model model) {

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }

        return "exception/error";
    }

    @GetMapping("/security-error")
    public String securityError(@RequestParam(name = "errorCode") String errorCode, Model model) {
        model.addAttribute("errorCode", errorCode);
        return "exception/securityError";
    }

}
