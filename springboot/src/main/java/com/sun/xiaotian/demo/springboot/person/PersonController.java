package com.sun.xiaotian.demo.springboot.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("person")
public class PersonController {

    private List<Person> persons = new ArrayList<>();

    private final Person NULL_PERSON = new Person("", 0);

    private final PersonService personService;
    private final UserDetailsService userDetailsService;
    private final JdbcTemplate jdbcTemplate;

    {
        persons.add(new Person("sunfeilong", 23));
        persons.add(new Person("longlongxiao", 24));
        persons.add(new Person("xiaotian", 25));
    }

    @Autowired
    public PersonController(PersonService personService, UserDetailsService userDetailsService, JdbcTemplate jdbcTemplate) {
        this.personService = personService;
        this.userDetailsService = userDetailsService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/")
    public String getName() {
        return "index.html";
    }

    @GetMapping("/persons")
    @ResponseBody
    public List<Person> getPersons() {
        return persons;
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    public Person delete(@PathVariable("name") String name) {
        if (name == null) {
            throw new IllegalArgumentException("name 不能为空!");
        }
        Person removePerson = NULL_PERSON;
        Iterator<Person> iterator = persons.iterator();

        while (iterator.hasNext()) {
            Person nextPerson = iterator.next();
            if (name.equals(nextPerson.name)) {
                removePerson = nextPerson;
                iterator.remove();
            }
        }
        return removePerson;
    }

    @PutMapping("")
    @ResponseBody
    public Person changeAge(String name, int age) {
        Person newPerson = new Person(name, age);
        persons.add(newPerson);
        return newPerson;
    }

    class Person implements Serializable {
        final String name;
        final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
