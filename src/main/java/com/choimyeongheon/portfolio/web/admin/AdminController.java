package com.choimyeongheon.portfolio.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/home")
    public String admin() {
        return "admin/home";
    }

    @GetMapping("/admin/works")
    public String works() {
        return "admin/works";
    }

    @GetMapping("/admin/home/save")
    public String save() {
        return "admin/save";
    }

}
