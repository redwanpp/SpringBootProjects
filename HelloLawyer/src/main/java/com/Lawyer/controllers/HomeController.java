package com.Lawyer.controllers;

import com.Lawyer.dao.ClientRepository;
import com.Lawyer.entities.Client;
import com.Lawyer.helper.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@Autowired
	private ClientRepository clientRepository;
	
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
        model.addAttribute("client", new Client());

        return "signup";
    }
    
    //handler for registering client
    @RequestMapping(value="/do_register", method=RequestMethod.POST)
    public String register(@Valid @ModelAttribute("client") Client client, BindingResult result, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
    	
    	try {
			
    		if(!agreement) {
        		System.out.println("You have not agreed with the terms and conditions");
        		
        		throw new Exception("You have not agreed with the terms and conditions");
        	}
    		
    		if(result.hasErrors()) {
    			
    			System.out.println("ERROR " + result.toString());
    			model.addAttribute("client", client);
    			
    			return "signup";
    		}
        	
        	client.setRole("Role_CLIENT");
        	client.setEnable(true);
        	client.setImageUrl("default.png");
        	client.setPassword(client.getPassword());
        	
        	System.out.println("Agreement: " +agreement);
        	System.out.println("Client: " + client);
        	
        	Client result1 = this.clientRepository.save(client);
        	
        	model.addAttribute("client", new Client());
        	
        	session.setAttribute("message", new Message("Successfully Registered", "alert-success"));
			
			return "signup";
        	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			model.addAttribute("client", client);
			session.setAttribute("message", new Message("Something went wrong !! " + e.getMessage(), "alert-danger"));
			
			return "signup";
		}
    	
    	
    }
    
}

