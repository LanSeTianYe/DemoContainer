package com.sun.xiaotain.demo.springcloud.hystrix.web;

import com.sun.xiaotain.demo.springcloud.hystrix.model.Person;
import com.sun.xiaotain.demo.springcloud.hystrix.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable("id") int id) {
        return personService.getById(id);
    }

    @GetMapping("one")
    public Person getOne() {
        return personService.getOne();
    }

    @GetMapping("oneSlowly")
    public Person getOneSlowly() {
        return personService.getOneSlowly();
    }

    @GetMapping("oneRandomException")
    public Person getOneRandomException() {
        return personService.getOneRandomException();
    }

    @GetMapping("list")
    public List<Person> list() {
        return personService.getAll();
    }
}


