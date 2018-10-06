package com.sun.xiaotain.demo.springcloud.hystrix.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
public class Person implements Serializable {

    private static final long serialVersionUID = -7702201688348949179L;

    public static final Person EmptyPerson = new Person();

    private Long id;
    private String name;
    private int age;
}
