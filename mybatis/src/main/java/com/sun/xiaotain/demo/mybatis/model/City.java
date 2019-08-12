package com.sun.xiaotain.demo.mybatis.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * city
 *
 * @author
 */
@Data
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String state;

    private String country;

    private LocalDateTime dateTime;

}