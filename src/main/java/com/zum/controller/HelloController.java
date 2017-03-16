package com.zum.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joeylee on 2017-03-16.
 */


@Controller
public class HelloController {

//    @RequestMapping("/")
//    public String hello() {
//
//        return "index";
//    }
    @RequestMapping("/")
    public String hello(Model model, @RequestParam(required = false) String msg) {
        model.addAttribute("message", msg);
        return "index";
    }

    @RequestMapping("/intro")
    public String intro() {
        return "pages/intro";
    }

}
