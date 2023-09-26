package com.springboot.thymeleafsecuritydemo.controller;

import com.springboot.thymeleafsecuritydemo.entity.User;
import com.springboot.thymeleafsecuritydemo.service.UserService;
import com.springboot.thymeleafsecuritydemo.user.WebUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class RegistrationController {
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/register")
    public String showRegistration(Model model){
        model.addAttribute("webUser", new WebUser());
        return "register/registration-form";
    }

    @PostMapping("/processRegistration")
    public String processRegistrationForm(@Valid @ModelAttribute("webUser") WebUser webUser,
                                          BindingResult result, Model model, HttpSession session){
    String userName = webUser.getUserName();
    if (result.hasErrors()){
        return "register/registration-form";
    }
    User existingUser = userService.findByUserName(userName);
    if (existingUser != null){
        model.addAttribute("webUser", new WebUser());
        model.addAttribute("registrationError", "Username already exists");
        return "register/registration-form";
    }
    userService.save(webUser);
    session.setAttribute("user", webUser);
    return "register/registration-confirmation";

    }

}
