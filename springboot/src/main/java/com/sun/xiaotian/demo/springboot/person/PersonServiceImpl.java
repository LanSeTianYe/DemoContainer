package com.sun.xiaotian.demo.springboot.person;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Value("${project.name}")
    private String projectName;

    @Value("${number}")
    private int number;

    @Override
    public String getName() {
        return projectName + " " + number + " ";
    }
}
