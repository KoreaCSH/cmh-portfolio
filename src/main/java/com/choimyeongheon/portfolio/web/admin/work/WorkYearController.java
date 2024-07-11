package com.choimyeongheon.portfolio.web.admin.work;

import com.choimyeongheon.portfolio.domain.work.service.WorkYearService;
import com.choimyeongheon.portfolio.web.admin.work.dto.WorkYearDeleteRequest;
import com.choimyeongheon.portfolio.web.admin.work.dto.WorkYearRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/work-year")
public class WorkYearController {

    private final WorkYearService workYearService;

    @GetMapping
    public String createForm(WorkYearRequest request, Model model) {
        model.addAttribute("request", request);
        return "admin/workyear/save";
    }

    @PostMapping
    public String create(@ModelAttribute("request") @Valid WorkYearRequest request) {
        workYearService.create(request);
        return "redirect:/admin/works";
    }

    @GetMapping("/delete-form")
    public String deleteForm(WorkYearDeleteRequest request, Model model) {
        request.setWorkYearDeleteDtoList(workYearService.findAllWorkYearDeleteDto());
        model.addAttribute("request", request);
        return "admin/workyear/delete";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("request") @Valid WorkYearDeleteRequest request) {
        workYearService.deleteAll(request);
        return "redirect:/admin/works";
    }

}
