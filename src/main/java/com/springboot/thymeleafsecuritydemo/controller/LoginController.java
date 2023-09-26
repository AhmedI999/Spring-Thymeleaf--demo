package com.springboot.thymeleafsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(){
        return "login-page";
    }
    @GetMapping("/denied")
    public String denyAccess(){
        return "denied";
    }

}
