package com.zum.controller;

import com.zum.domain.User;
import com.zum.repository.UserRepository;
import com.zum.service.UserServcieImpl;
import com.zum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by joeylee on 2017-03-21.
 */
@Controller
public class SecurityTestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage(Authentication auth) {
        ModelAndView model = new ModelAndView();
        if(auth != null && auth.getName() != null) {
            model.addObject("message", auth.getName() + " 입니다.");
        }
        else  {
            model.addObject("message", "please login");
        }

        model.setViewName("home");
        return model;
    }
    @RequestMapping("/login")
    public void login() {}

    @RequestMapping("/registerForm")
    public void registerForm() {}

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user) {

        if(userService.create(user)) {

            return "redirect:/";
        }
        else {
            //수정해야함
            System.out.println("error");
            return "redirect:/";
        }

    }



}
