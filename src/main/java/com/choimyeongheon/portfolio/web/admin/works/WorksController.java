package com.choimyeongheon.portfolio.web.admin.works;

import com.choimyeongheon.portfolio.domain.work.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WorksController {

    private final WorkService workService;

    @GetMapping("/admin/works")
    public String works() {
        return "admin/works";
    }

}
