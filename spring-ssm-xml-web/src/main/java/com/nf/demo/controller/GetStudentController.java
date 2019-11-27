package com.nf.demo.controller;

import com.github.pagehelper.PageInfo;
import com.nf.demo.entity.Student;
import com.nf.demo.service.StudentService;
import com.nf.demo.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GetStudentController {

    @Autowired
    private StudentServiceImpl service;

    @RequestMapping("/index")
    public String index(@RequestParam(value = "pageNum",required = false,defaultValue = "1")int pageNum,
                        @RequestParam(value = "pageSize",required = false,defaultValue = "3") int pageSize,
                        Model model) {
        System.out.println("service = " + service);
        List<Student> all = service.getAll(pageNum, pageSize);
        for (Student student : all) {
            System.out.println("student = " + student);
        }
        PageInfo<Student> pageInfo = new PageInfo(all);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }

    @RequestMapping("/aaa")
    public String aaa() {
        return "forward:/WEB-INF/jsp/first.jsp";
    }
}
