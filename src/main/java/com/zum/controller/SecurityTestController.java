package com.zum.controller;

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

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView welcomePage(Authentication auth) {
        ModelAndView model = new ModelAndView();
        if(auth != null && auth.getName() != null) {
            model.addObject("message", "welcome " + auth.getName() + " user");
        }
        else  {
            model.addObject("message", "welcome anonymous user");
        }

        model.setViewName("home");

        return model;
    }
}
