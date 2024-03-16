package com.filmreview.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
