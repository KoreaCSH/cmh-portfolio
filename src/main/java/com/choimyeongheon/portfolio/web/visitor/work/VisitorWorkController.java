package com.choimyeongheon.portfolio.web.visitor.work;

import com.choimyeongheon.portfolio.domain.work.service.WorkService;
import com.choimyeongheon.portfolio.web.visitor.work.dto.VisitorWorkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/works")
public class VisitorWorkController {

    private final WorkService workService;

    @GetMapping
    public String works(Model model) {
        List<VisitorWorkResponse> works = workService.findAllPublicWorkResponse();
        model.addAttribute("works", works);
        return "works";
    }

}
