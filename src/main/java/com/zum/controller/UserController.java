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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by joeylee on 2017-03-21.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/new")
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/")
    public String register(@Valid User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            logger.info(" 유효성 에러 ");
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                logger.debug("error:{}",error.getDefaultMessage());
            }
            return "registerForm";
        }


        else {
            userService.create(user);
            return "redirect:/";
        }


    }

    @GetMapping("/edit")
    public String updateForm(Model model, Authentication auth) {

        String userId = auth.getName();
        User user = userService.getUserByUsername(userId);
        model.addAttribute("user", user);

        return "updateForm";

    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long userId,
                         @RequestParam String password, @RequestParam String email) {

        logger.debug("User : " + password + email);
//        if(userService.update(user)) {
//
//            return "redirect:/";
//        }
//        else {
//            //수정해야함
//            System.out.println("error");
//            return "redirect:/";
//        }
        return "redirect:/";

    }

    @GetMapping("/{id}")
    public String leave(@PathVariable("id") Long id) {

        userService.leave(id);
        SecurityContextHolder.clearContext();

        return "redirect:/";
    }



}
