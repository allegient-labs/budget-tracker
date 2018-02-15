package com.dminc.dts.budget.tracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/callback")
public class LoginController {

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

}
