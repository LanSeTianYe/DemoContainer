package com.xiaotian.datasource.dynamic.controller;


import com.xiaotian.datasource.dynamic.entity.Person;
import com.xiaotian.datasource.dynamic.service.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("persons")
    public List<Person> persons(String datasource) {
        return personService.getAll();
    }
}
