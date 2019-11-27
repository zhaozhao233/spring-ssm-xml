package com.nf.cf.dao;

import com.nf.cf.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {

    List<Student> getAll(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);

    void deleteById(@Param("id")int id);

    void updateById(@Param("id")int id);
}
