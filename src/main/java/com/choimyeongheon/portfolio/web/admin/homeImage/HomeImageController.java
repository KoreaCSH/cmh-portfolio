package com.choimyeongheon.portfolio.web.admin.homeImage;

import com.choimyeongheon.portfolio.domain.homeImage.domain.HomeImage;
import com.choimyeongheon.portfolio.domain.homeImage.service.HomeImageService;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageDeletionDto;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageDeletionRequest;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageMapper;
import com.choimyeongheon.portfolio.web.admin.homeImage.dto.HomeImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeImageController {

    private final HomeImageService homeImageService;
    private final HomeImageMapper homeImageMapper;

    @GetMapping("/admin/home")
    public String admin(Model model) {

        List<HomeImage> homeImages = homeImageService.findAll();
        model.addAttribute("homeImages", homeImages);

        return "admin/homeImage/home";
    }

    @GetMapping("/admin/home/save")
    public String saveForm(Model model, HomeImageRequest request) {
        model.addAttribute("request", request);
        return "admin/homeImage/save";
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

    @GetMapping("/admin/home/delete")
    public String deleteForm(Model model, HomeImageDeletionRequest request) {

        List<HomeImageDeletionDto> homeImageDeletionDtoList =
                homeImageService.findAll()
                                .stream()
                                .map(homeImage -> new HomeImageDeletionDto(homeImage.getId(), homeImage.getFileName(), homeImage.getTitle()))
                                .collect(Collectors.toList());

        request.setHomeImageDeletionDtoList(homeImageDeletionDtoList);

        model.addAttribute("request", request);

        return "admin/homeImage/delete";
    }

    @PostMapping("/admin/home/delete")
    public String delete(@ModelAttribute("request") HomeImageDeletionRequest request) {

        List<Long> deletedHomeImageIdList =
                request.getHomeImageDeletionDtoList()
                        .stream()
                        .filter(HomeImageDeletionDto::isDeleted)
                        .map(HomeImageDeletionDto::getId)
                        .collect(Collectors.toList());

        // list 가 empty 일 경우 alert 창


        // service 삭제 로직
        homeImageService.deleteAllByIds(deletedHomeImageIdList);

        return "redirect:/admin/home";
    }

}
