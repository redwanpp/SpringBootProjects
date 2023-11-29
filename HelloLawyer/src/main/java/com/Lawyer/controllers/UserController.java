package com.Lawyer.controllers;

import com.Lawyer.dao.ClientRepository;
import com.Lawyer.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ClientRepository clientRepository;


	@PostMapping("/index")
	public String dashboard(Model model, Principal principal, @ModelAttribute("client") Client client) {

        Client client1 = clientRepository.findByEmail(client.getEmail());

        if(client.getPassword().equals(client1.getPassword())) {
            System.out.println("Client : " + client1);

            model.addAttribute("client", client1);

            return "normal/user_dashboard";
        }
        else {
            return "login";
        }
	}
}
