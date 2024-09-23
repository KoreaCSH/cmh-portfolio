package com.choimyeongheon.portfolio.web.visitor.work;

import com.choimyeongheon.portfolio.domain.work.service.WorkService;
import com.choimyeongheon.portfolio.web.visitor.work.dto.VisitorWorkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/works")
public class VisitorWorkController {

    private final WorkService workService;

    @GetMapping("/all")
    public String works(Model model) {
        List<VisitorWorkResponse> works = workService.findAllPublicWorkResponse();
        model.addAttribute("works", works);
        model.addAttribute("selectedYear", "all");
        return "works";
    }

    @GetMapping("/{workYear}")
    public String worksByWorkYear(Model model, @PathVariable(name = "workYear") Integer workYear) {
        List<VisitorWorkResponse> works = workService.findPublicWorkResponseByWorkYear(workYear);
        model.addAttribute("works", works);
        model.addAttribute("selectedYear", workYear);
        return "works";
    }

}
