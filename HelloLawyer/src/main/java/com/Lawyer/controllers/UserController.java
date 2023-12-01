package com.Lawyer.controllers;

import com.Lawyer.dao.ClientRepository;
import com.Lawyer.entities.Client;
import com.Lawyer.entities.ClientPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/user")
public class UserController {
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

    @Autowired
    private ClientRepository clientRepository;

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
    public String processClientPost(@ModelAttribute ClientPost clientPost, @RequestParam("clientPostImage")MultipartFile multipartFile, Model model) {

        Client client1 = clientRepository.findByEmail(userMail);


        if(pass.equals(client1.getPassword())) {
            try {
                System.out.println("Client : " + client1);
//
//                // processing image
//
//                if (multipartFile.isEmpty()) {
//                    //print message here
//                    System.out.println("File is empty");
//                } else {
//                    // upload the file in folder & update the table
//                    clientPost.setImgUrl(multipartFile.getOriginalFilename());
//
//                    File file = new ClassPathResource("static/img/clientPostImg").getFile();
//
//                    Path path = Paths.get(file.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename());
//
//                    Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//
//                    System.out.println("Image is uploaded");
//                }

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
}
