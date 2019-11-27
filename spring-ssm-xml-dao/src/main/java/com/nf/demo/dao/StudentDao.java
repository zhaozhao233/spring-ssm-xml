package com.nf.demo.dao;

import com.nf.demo.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {

    List<Student> getAll(@Param("pageNum")int pageNum,@Param("pageSize")int pageInfo);

    void deleteById(@Param("id") int id);
}
