package com.sun.xiaotian.demo.springboot.person;

import com.sun.xiaotian.demo.springboot.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("person/")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/")
    public String getName() {
        return "index.html";
    }

    @RequestMapping("/persons")
    public List<Person> getPersons() {
        return personService.getAll();
    }

    @DeleteMapping("/{name}")
    public HttpResult delete(@PathVariable("name") String name) {
        if (name.equals("123")) {
            throw new IllegalArgumentException("name 不能为空!");
        }
        return new HttpResult(true, personService.deleteByName(name));
    }
}
