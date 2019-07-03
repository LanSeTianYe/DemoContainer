package com.xiaotian.datasource.dynamic.controller;

import com.xiaotian.datasource.dynamic.commoon.Result;
import com.xiaotian.datasource.dynamic.entity.Person;
import com.xiaotian.datasource.dynamic.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class PersonController {

    private final PersonService personService;
    private final JdbcTemplate jdbcTemplate;

    public PersonController(PersonService personService, JdbcTemplate jdbcTemplate) {
        this.personService = personService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/persons")
    public Result persons() {
        return Result.ofData(personService.getAll());
    }

    @RequestMapping("/allPerson")
    public Result allPerson() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from person;");
        return Result.ofData(result);
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
