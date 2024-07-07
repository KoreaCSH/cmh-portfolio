package com.choimyeongheon.portfolio.web.admin.work;

import com.choimyeongheon.portfolio.domain.work.service.WorkYearService;
import com.choimyeongheon.portfolio.web.admin.work.dto.WorkYearRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/work-year")
public class WorkYearController {

    private final WorkYearService workYearService;

    @GetMapping
    public String createForm(WorkYearRequest request, Model model) {

        model.addAttribute("request", request);
        return "admin/work/workYear";
    }

    @PostMapping
    public String create(@ModelAttribute("request") @Valid WorkYearRequest request) {

        workYearService.create(request);
        return "redirect:/admin/works";
    }

}
