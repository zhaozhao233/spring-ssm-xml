package com.nf.cf.controller;

import com.nf.cf.service.StudentService;
import com.nf.cf.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
    @Autowired
    private StudentServiceImpl service;

    @RequestMapping("/index")
    public String index() {
        System.out.println("进入控制器");

        return "index";
    }

}
