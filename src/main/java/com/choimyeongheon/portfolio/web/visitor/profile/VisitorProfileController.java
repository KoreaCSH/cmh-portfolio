package com.choimyeongheon.portfolio.web.visitor.profile;

import com.choimyeongheon.portfolio.domain.profile.service.ProfileService;
import com.choimyeongheon.portfolio.web.visitor.profile.dto.VisitorProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class VisitorProfileController {

    private final ProfileService profileService;

    @GetMapping
    public String profiles(Model model) {
        List<VisitorProfileResponse> response = profileService.findAllVisitorProfileResponse();
        model.addAttribute("response", response);
        return "profiles";
    }

}
