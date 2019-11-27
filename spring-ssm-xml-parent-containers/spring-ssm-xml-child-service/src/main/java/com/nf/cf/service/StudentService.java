package com.nf.cf.service;

import com.nf.cf.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAll(int pageNum,int pageSize);

    void deleteById(int id);

    void updateById();
}
