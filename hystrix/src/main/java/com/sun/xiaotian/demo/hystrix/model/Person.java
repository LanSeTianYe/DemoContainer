package com.sun.xiaotian.demo.hystrix.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors
public class Person implements Serializable {

    private static final long serialVersionUID = -9018165045118212931L;

    private String name;
    private int age;
}
