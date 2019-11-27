package com.servlet;

import com.nf.demo.dao.StudentDao;
import com.nf.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-spring.xml")
public class StudentServeltTest {

    @Autowired
    private StudentDao dao;
    @Test
    public void index() {
        List<Student> all = dao.getAll(1, 5);
        for (Student student : all) {
            System.out.println("student = " + student);
        }
    }

}
