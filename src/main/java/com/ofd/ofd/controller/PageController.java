package com.ofd.ofd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ofd.ofd.model.User;
import com.ofd.ofd.service.Manager;

@Controller
public class PageController {
    private Manager manager;

    @Autowired
    public PageController(Manager manager) {
        this.manager = manager;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        return "main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/profile")
    public String profilePage() {
        // User user = manager.findUserById(id);
        // model.addAttribute("balance", user.getBalance());
        return "profile";
    }
}
