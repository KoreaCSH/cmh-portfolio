package com.choimyeongheon.portfolio.web.admin.homeImage;

import com.choimyeongheon.portfolio.domain.homeImage.service.HomeImageService;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeImageController {

    private final HomeImageService homeImageService;

    @GetMapping("/admin/home-images")
    public String admin(Model model, HomeImageUpdateRequest request) {

        List<HomeImageResponse> homeImages = homeImageService.findAll();
        model.addAttribute("homeImages", homeImages);
        model.addAttribute("request", request);

        return "admin/homeImage/home";
    }

    @GetMapping("/admin/home-images/save-form")
    public String saveForm(Model model, HomeImageSaveRequest request) {

        List<HomeImageResponse> homeImages = homeImageService.findAll();
        model.addAttribute("homeImages", homeImages);
        model.addAttribute("request", request);

        return "admin/homeImage/save";
    }

    @PostMapping("/admin/home-images")
    public String save(@ModelAttribute("request") @Valid HomeImageSaveRequest request) {

        // 용량 제한 관련 Exception 처리 필요

        homeImageService.createHomeImage(request);
        return "redirect:/admin/home-images";
    }

    @PutMapping("/admin/home-images")
    public String update(@ModelAttribute("request") @Valid HomeImageUpdateRequest request) {

        homeImageService.updateHomeImage(request);
        return "redirect:/admin/home-images";
    }

    @GetMapping("/admin/home-images/delete-form")
    public String deleteForm(Model model, HomeImageDeletionRequest request) {

        request.setHomeImageDeletionDtoList(homeImageService.findAllDeletionDto());
        model.addAttribute("request", request);

        return "admin/homeImage/delete";
    }

    @DeleteMapping("/admin/home-images")
    public String delete(@ModelAttribute("request") HomeImageDeletionRequest request) {

        homeImageService.deleteAllByIds(request.getHomeImageDeletionDtoList());

        return "redirect:/admin/home-images";
    }

    @GetMapping("/admin/home-images/display")
    public ResponseEntity<Resource> display(@RequestParam("fileName") String fileName) {

        return homeImageService.display(fileName);
    }

}
