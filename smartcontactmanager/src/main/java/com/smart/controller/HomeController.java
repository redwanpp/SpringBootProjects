package com.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//handler for home page
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("title", "Home - Smart Contact Manager");
		
		return "home";
	}

	//handler for about page
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "Home - Smart Contact Manager");
		
		return "about";
	}
	
	//handler for signup page
	@GetMapping("/signup/")
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart Contact Manager");
		
		return "signup";
	}
}
