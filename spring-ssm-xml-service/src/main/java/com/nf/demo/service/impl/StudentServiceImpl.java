package com.nf.demo.service.impl;

import com.nf.demo.dao.StudentDao;
import com.nf.demo.entity.Student;
import com.nf.demo.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao dao;

    @Override
    public List<Student> getAll(int pageNum, int pageInfo) {
        return dao.getAll(pageNum,pageInfo);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }
}
