package com.sun.xiaotian.demo.springboot.person;

import com.sun.xiaotian.demo.springboot.common.HttpResult;
import com.sun.xiaotian.demo.springboot.hystrix.GetAllUserHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person/")
@Scope("request")
public class PersonController {

    private final PersonService personService;
    private final GetAllUserHystrixCommand getAllUserHystrixCommand;

    public PersonController(PersonService personService, GetAllUserHystrixCommand getAllUserHystrixCommand) {
        this.personService = personService;
        this.getAllUserHystrixCommand = getAllUserHystrixCommand;
    }

    @RequestMapping("")
    public String getName() {
        return "index.html";
    }

    @RequestMapping("persons")
    public HttpResult getPersons() {
        return new HttpResult(true, getAllUserHystrixCommand.execute());
    }

    @DeleteMapping("{name}")
    public HttpResult delete(@PathVariable("name") String name) {
        if (name.equals("123")) {
            throw new IllegalArgumentException("name 不能为空!");
        }
        return new HttpResult(true, personService.deleteByName(name));
    }

    @RequestMapping("testMethodArgument")
    public HttpResult testMethodArgument(Person person) {
        return new HttpResult(true, person);
    }
}
