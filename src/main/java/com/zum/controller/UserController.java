package com.zum.controller;


import com.zum.domain.User;
import com.zum.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by joeylee on 2017-03-16.
 */

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/new")
    public String join(HttpServletRequest request) {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        userDao.save(new User(userId,password,email));

        return "/index";
    }

}
