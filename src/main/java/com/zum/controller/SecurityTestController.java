package com.zum.controller;

import com.zum.domain.User;
import com.zum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by joeylee on 2017-03-21.
 */
@Controller
public class SecurityTestController {

    Logger logger = LoggerFactory.getLogger(SecurityTestController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage(Authentication auth) {
        ModelAndView model = new ModelAndView();
        if(auth != null && auth.getName() != null) {
            model.addObject("message", auth.getName() + " 입니다.");
        }


        model.setViewName("home");
        return model;
    }


    @RequestMapping("/login")
    public void login() {


    }

    @RequestMapping("/registerForm")
    public void registerForm() {}

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            return "registerForm";
        }
        if(userService.create(user)) {


            return "redirect:/";
        }
        else {
            //수정해야함
            System.out.println("error");
            return "redirect:/";
        }


    }

    @RequestMapping("/updateForm")
    public void updateForm(Model model, Authentication auth) {

        String userId = auth.getName();
        User user = userService.getUserByUsername(userId);
        model.addAttribute("user", user);

    }

    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public String update(User user) {


        if(userService.update(user)) {

            return "redirect:/";
        }
        else {
            //수정해야함
            System.out.println("error");
            return "redirect:/";
        }

    }

    @RequestMapping("/leave/{id}")
    public String leave(@PathVariable("id") Long id, HttpSession session) {

        userService.leave(id);

        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }


        return "redirect:/";
    }



}
