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
public class MainController {

    @RequestMapping("/")
    public String hello() {

        return "index";
    }

    @RequestMapping("/board")
    public String board() {
        return "pages/board";
    }

    @RequestMapping("/join")
    public String join() {
        return "pages/join";
    }



}
