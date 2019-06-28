package com.xiaotian.datasource.dynamic.controller;

import com.xiaotian.datasource.dynamic.commoon.Result;
import com.xiaotian.datasource.dynamic.entity.Person;
import com.xiaotian.datasource.dynamic.service.PersonService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/persons")
    public Result persons() {
        return Result.ofData(personService.getAll());
    }

    @RequestMapping("/person/add")
    public Result addPerson(String personName) {
        if (StringUtils.isEmpty(personName)) {
            throw new RuntimeException("add person, person name is null");
        }
        Person person = new Person();
        person.setName(personName);
        personService.insertUser(person);
        return Result.ofData("添加成功");
    }
}
