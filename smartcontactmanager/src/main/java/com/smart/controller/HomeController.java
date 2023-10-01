package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userRepository;
	
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
		model.addAttribute("user", new User());
		
		return "signup";
	}
	
	//handler for registering user
	@PostMapping("/do_register")
	public String registerUser(@ModelAttribute("user") User user,@RequestParam(value="agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
		
		try {
			if(!agreement) {
				System.out.println("You have not agreed with terms and condition");
				throw new Exception("You have not agreed with terms and condition");
			}
			
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setImageUrl("default.png");
			
			
			System.out.println("Agreement " + agreement);
			System.out.println("User " + user);
			
			User result = this.userRepository.save(user);
			
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Succesfully singup !!" , "alert-succes"));
			
			return "signup";
		} catch(Exception e) {
			e.printStackTrace();
			
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Somting went wrong !!" + e.getMessage(), "alert-danger"));
			
			return "signup";
		}
	}
}
