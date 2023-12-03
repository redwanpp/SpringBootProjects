package com.Lawyer.controllers;

import com.Lawyer.dao.ClientPostRepository;
import com.Lawyer.dao.ClientRepository;
import com.Lawyer.entities.Client;
import com.Lawyer.entities.ClientPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientPostRepository clientPostRepository;
    private String userMail;
    private String pass;

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void addCommonData(Model model, @ModelAttribute("client") Client client) {
        Client client1 = clientRepository.findByEmail(client.getEmail());
        model.addAttribute("client", client);
    }


	@RequestMapping("/index")
	public String dashboard(Model model, @ModelAttribute("client") Client client) {
        model.addAttribute("title", "User Dashboard");
        if (userMail == null) {
            userMail=client.getEmail();
            pass=client.getPassword();
        }

        Client client1 = clientRepository.findByEmail(userMail);


        if(pass.equals(client1.getPassword())) {
            System.out.println("Client : " + client1);

            model.addAttribute("client", client1);

            return "normal/user_dashboard";
        }
        else {
            userMail=null;
            pass=null;

            return "login";
        }
	}

    // open post form handler
    @GetMapping("/add-client-post")
    public String openClientPostForm(Model  model) {
        Client client1 = clientRepository.findByEmail(userMail);


        if(pass.equals(client1.getPassword())) {
            System.out.println("Client : " + client1);

            model.addAttribute("client", client1);
            model.addAttribute("title", "add post");
            model.addAttribute("clientPost", new ClientPost());

            return "normal/add_post_form";
        }
        else {
            userMail=null;
            pass=null;

            return "login";
        }

    }


    // Process post from client
    @PostMapping("/process-client-post")
    public String processClientPost(@ModelAttribute ClientPost clientPost, Model model) {

        Client client1 = clientRepository.findByEmail(userMail);


        if(pass.equals(client1.getPassword())) {
            try {
                System.out.println("Client : " + client1);

                clientPost.setClient(client1);

                client1.getPosts().add(clientPost);

                this.clientRepository.save(client1);

                System.out.println(clientPost);

                model.addAttribute("client", client1);
                model.addAttribute("title", "add post");
            } catch (Exception e) {
                System.out.println("ERROR " + e.getMessage());
                e.printStackTrace();
            }

            return "normal/add_post_form";
        }
        else {
            userMail=null;
            pass=null;

            return "login";
        }
    }


    //Show client post handler
    @RequestMapping("/view-post")
    public String showClientPost(Model model, @ModelAttribute("client") Client client) {
        model.addAttribute("title", "View post");

        Client client1 = clientRepository.findByEmail(userMail);


        if(pass.equals(client1.getPassword())) {
            System.out.println("Client : " + client1);

            model.addAttribute("client", client1);

            List<ClientPost> clientPosts = this.clientPostRepository.findClientPostByClient(client1.getId());

            model.addAttribute("clientPosts", clientPosts);

            return "normal/show_client_post";
        }
        else {
            userMail=null;
            pass=null;

            return "login";
        }
    }

}
