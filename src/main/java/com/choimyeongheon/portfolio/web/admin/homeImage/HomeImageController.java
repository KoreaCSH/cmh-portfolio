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

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeImageController {

    private final HomeImageService homeImageService;
    private final HomeImageMapper homeImageMapper;

    @GetMapping("/admin/home-images")
    public String admin(Model model, HomeImageUpdateRequest request) {

        List<HomeImageResponse> homeImages = homeImageService.findAll();
        model.addAttribute("homeImages", homeImages);
        model.addAttribute("request", request);

        return "admin/homeImage/home";
    }

    @GetMapping("/admin/home-images/save")
    public String saveForm(Model model, HomeImageRequest request) {
        model.addAttribute("request", request);
        return "admin/homeImage/save";
    }

    @PostMapping("/admin/home-images/save")
    public String save(@ModelAttribute("request") @Valid HomeImageRequest request) {

        // homeImageResponse 객체 생성 후 변경 - Entity 를 반환하지 않기
        homeImageService.createHomeImage(request);

        return "redirect:/admin/home-images";
    }

    @PostMapping("/admin/home-images/update")
    public String update(@ModelAttribute("request") @Valid HomeImageUpdateRequest request) {

        // Post 요청으로 하는 것이 옳은가. Patch 를 해야 하지 않을까

        homeImageService.updateHomeImage(request);
        return "redirect:/admin/home-images";
    }

    @GetMapping("/admin/home-images/display")
    public ResponseEntity<Resource> display(@RequestParam("fileName") String fileName) throws IOException {

        // service 객체만 Mapper 를 의존하도록 로직을 변경하자.
        // 즉, service 객체가 Resource 반환하도록 변경하기.

        return homeImageMapper.toResource(fileName);
    }

    @GetMapping("/admin/home-images/delete")
    public String deleteForm(Model model, HomeImageDeletionRequest request) {

        request.setHomeImageDeletionDtoList(homeImageService.findAllDeletionDto());
        model.addAttribute("request", request);

        return "admin/homeImage/delete";
    }

    @PostMapping("/admin/home-images/delete")
    public String delete(@ModelAttribute("request") HomeImageDeletionRequest request) {

        homeImageService.deleteAllByIds(request.getHomeImageDeletionDtoList());

        return "redirect:/admin/home-images";
    }

}
