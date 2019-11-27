package com.nf.demo.service;

import com.nf.demo.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAll(int pageNum,int pageInfo);

    void deleteById(int id);

}
