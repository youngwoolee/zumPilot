package com.zum.controller;

import com.zum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * Created by joeylee on 2017-04-06.
 */
@Controller
public class MainController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String welcomePage(Model model, Authentication auth) {

        if(auth != null && auth.getName() != null) {
            model.addAttribute("message", auth.getName()+"입니다.");
        }
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}