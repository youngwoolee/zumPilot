package com.zum.controller;

import com.zum.domain.User;
import com.zum.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * Created by joeylee on 2017-03-21.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/new")
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/")
    public String register(@Valid User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            logger.debug(" 유효성 에러 ");
            return "registerForm";
        }
        else {
            userService.create(user);
            return "redirect:/";
        }
    }

    @GetMapping("/edit")
    public String updateForm(Model model, Authentication auth) {

        User user = userService.getUserByUsername(auth.getName());
        model.addAttribute("user", user);

        return "updateForm";

    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long userId,
                         @RequestParam String password,
                         @RequestParam String email,
                         Authentication auth) {

        //진짜 사용자인지 권한 체크
        userService.isAuthenticated(auth.getName(), userId);
        userService.update(userId, password, email);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public ResponseEntity leave(@PathVariable("id") Long userId, Authentication auth) {

        userService.isAuthenticated(auth.getName(), userId);
        userService.leave(userId);
        SecurityContextHolder.clearContext();

        HashMap<String, Object> result = new HashMap<>();
        result.put("url","/");

        return new ResponseEntity<HashMap>(result, HttpStatus.OK);

//        return "redirect:/";
    }

    @PostMapping("/isDuplicate")
    @ResponseBody
    public ResponseEntity isDuplicate(@RequestParam("userName") String userName) {

        logger.error("userName  : {} ", userName);

        boolean result = userService.isExist(userName);



        return new ResponseEntity(result, HttpStatus.OK);
    }



}
