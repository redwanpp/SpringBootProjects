package com.Lawyer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(Model model) {

        model.addAttribute("title","Home - Hello Lawyer");

        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {

        model.addAttribute("title","About - Hello Lawyer");

        return "about";
    }
    
    @RequestMapping("/signup")
    public String signup(Model model) {

        model.addAttribute("title","Register - Hello Lawyer");

        return "signup";
    }
}
