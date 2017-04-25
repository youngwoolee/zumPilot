package com.zum.controller;

import com.zum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by joeylee on 2017-04-06.
 */
@Controller
public class MainController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String welcomePage(Model model, Authentication auth) {

        if(auth != null && auth.getName() != null) {
            model.addAttribute("message", auth.getName()+"입니다.");
        }
        return "home";
    }

    @GetMapping("/loginPage")
    public String login() {
        return "loginPage";
    }

}
