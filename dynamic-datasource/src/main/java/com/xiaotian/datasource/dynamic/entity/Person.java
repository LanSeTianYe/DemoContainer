package com.xiaotian.datasource.dynamic.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Person {
    @Id
    private Long id;

    @Column
    private String name;
}
