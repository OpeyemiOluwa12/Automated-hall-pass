package com.opeyemi.automatedhallpass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @RequestMapping("/login")
    public String getLogin(Model model){
//        model.addAttribute("student-login");
        return "student-login";
    }
}
