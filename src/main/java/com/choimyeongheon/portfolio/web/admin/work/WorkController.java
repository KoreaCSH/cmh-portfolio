package com.choimyeongheon.portfolio.web.admin.work;

import com.choimyeongheon.portfolio.domain.work.domain.WorkYear;
import com.choimyeongheon.portfolio.domain.work.service.WorkService;
import com.choimyeongheon.portfolio.domain.work.service.WorkYearService;
import com.choimyeongheon.portfolio.web.admin.work.dto.WorkResponse;
import com.choimyeongheon.portfolio.web.admin.work.dto.WorkSaveRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;
    private final WorkYearService workYearService;

    @GetMapping("/admin/works")
    public String works(Model model) {
        List<WorkResponse> works = workService.findAllByOrderByWorkDateDesc();
        List<WorkYear> workYears = workYearService.findAll();
        model.addAttribute("works", works);
        model.addAttribute("workYears", workYears);

        return "admin/work/works";
    }

    @GetMapping("/admin/works/save-form")
    public String saveForm(Model model, WorkSaveRequest request) {
        model.addAttribute("request", request);
        return "admin/work/save";
    }

    @PostMapping("/admin/works")
    public String save(@ModelAttribute("request") @Valid WorkSaveRequest request) {
        // view 에서 request 의 workDate 입력 받기 구현
        workService.createWork(request);
        return "redirect:/admin/works";
    }

    @GetMapping("/admin/works/display")
    public ResponseEntity<Resource> display(@RequestParam("fileName") String fileName) {
        return workService.display(fileName);
    }

}
