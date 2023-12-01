package com.Lawyer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    UserController userController = new UserController();

    @GetMapping("/sign-out")
    public String logout(Model model) {
        userController.setUserMail(null);
        userController.setPass(null);

        System.out.println("userMail: " + userController.getUserMail());
        System.out.println("pass: " + userController.getPass());

        return "logout";
    }
}
