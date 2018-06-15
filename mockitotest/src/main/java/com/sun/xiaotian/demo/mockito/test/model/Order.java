package com.sun.xiaotian.demo.mockito.test.model;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors
public class Order {

    private Long num;

    private String name;

    private Date createTime;
}
