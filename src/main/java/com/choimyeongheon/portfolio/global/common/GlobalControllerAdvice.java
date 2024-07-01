package com.choimyeongheon.portfolio.global.common;

import com.choimyeongheon.portfolio.domain.work.domain.WorkYear;
import com.choimyeongheon.portfolio.domain.work.service.WorkYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final WorkYearService workYearService;

    @ModelAttribute
    public void getWorkYears(Model model) {
        List<WorkYear> workYears = workYearService.findAll();
        model.addAttribute("workYears", workYears);
    }

}
