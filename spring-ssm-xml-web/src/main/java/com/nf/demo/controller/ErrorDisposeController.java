package com.nf.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorDisposeController {

    @RequestMapping("/404")
    public String error404() {
        return "404";
    }
}
