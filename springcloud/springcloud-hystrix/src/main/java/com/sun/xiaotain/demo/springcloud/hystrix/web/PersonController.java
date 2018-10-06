package com.sun.xiaotain.demo.springcloud.hystrix.web;

import com.sun.xiaotain.demo.springcloud.hystrix.model.Person;
import com.sun.xiaotain.demo.springcloud.hystrix.service.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person/annotation")
public class PersonController {

    private final PersonService personService;

    public PersonController(@Qualifier("PersonServiceImplOfAnnotation") PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("{id}")
    public Person get(@PathVariable("id") int id) {
        return personService.getById(id);
    }

    @PutMapping("{id}")
    public Person get(@PathVariable("id") int id, String name) {
        return personService.updatePerson(id, name);
    }

    @PostMapping("refresh")
    public void refresh() {
        personService.refreshList();
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


