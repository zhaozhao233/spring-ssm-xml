package com.nf.cf.service.impl;

import com.nf.cf.entity.Student;
import com.nf.cf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentServiceImpl service;

    public List<Student> getAll(int pageNum, int pageSize) {
        return service.getAll(pageNum,pageSize);
    }

    public void deleteById(int id) {
        service.deleteById(id);
    }

    public void updateById() {

    }
}
