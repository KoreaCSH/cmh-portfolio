package com.choimyeongheon.portfolio.web.admin;

import com.choimyeongheon.portfolio.domain.home.domain.HomeImage;
import com.choimyeongheon.portfolio.domain.home.service.HomeImageService;
import com.choimyeongheon.portfolio.web.home.dto.HomeImageMapper;
import com.choimyeongheon.portfolio.web.home.dto.HomeImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final HomeImageService homeImageService;
    private final HomeImageMapper homeImageMapper;

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

    @GetMapping("/admin/home/display")
    public ResponseEntity<Resource> display(@RequestParam("fileName") String fileName) throws IOException {

        return homeImageMapper.toResource(fileName);
    }

}
