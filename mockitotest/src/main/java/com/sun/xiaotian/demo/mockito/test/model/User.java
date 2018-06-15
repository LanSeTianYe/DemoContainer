package com.sun.xiaotian.demo.mockito.test.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors
public class User {

    private Long id;

    private String userName;

    private int age;

    private List<Order> orderList;
}
