package com.nf.cf.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Student {

    private Integer id;
    private String name;
    private Integer sex;
    private Integer age;
    private Date date;
}
