package com.sun.xiaotian.demo.hystrix.web;


import com.sun.xiaotian.demo.hystrix.base.ResultT;
import com.sun.xiaotian.demo.hystrix.service.PersonService;
import com.sun.xiaotian.demo.hystrix.util.PercentUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

    private final PersonService personService;

    private PercentUtil percentUtil = PercentUtil.ofSuccessAndFaildTimes(999, 1);

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResultT getOne(@PathVariable("id") String id) {
        if (!percentUtil.next()) {
            throw new RuntimeException("模拟系统异常");
        }
        return ResultT.ofData(personService.getById(id).block());
    }

}
