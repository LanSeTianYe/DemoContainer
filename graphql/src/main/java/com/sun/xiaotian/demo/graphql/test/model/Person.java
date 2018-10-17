package com.sun.xiaotian.demo.graphql.test.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
public class Person implements Serializable {

    private static final long serialVersionUID = 3783596138469723317L;

    private Long id;        //主键Id
    private String name;    //姓名
    private int age;        //年龄
}
