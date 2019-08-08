package com.xiaotian.demo.mock.http.controller;

import com.xiaotian.demo.mock.http.vo.PersonVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {

    private List<PersonVo> personVoList = new ArrayList<>();

    @PostConstruct
    public void init() {
        PersonVo personVo = new PersonVo();
        personVo.setName("zhangsan");
        personVo.setAge(20);
        personVoList.add(personVo);
        personVo = new PersonVo();
        personVo.setName("lisi");
        personVo.setAge(21);
        personVoList.add(personVo);
    }


    @RequestMapping(value = "/getPersons", method = {RequestMethod.GET})
    @ResponseBody
    public List<PersonVo> getPersons() {
        return personVoList;
    }

    @RequestMapping(value = "/getPersonByName_Get", method = {RequestMethod.GET})
    @ResponseBody
    public PersonVo getPersonByName_Get(String name, Integer age) {
        PersonVo personVo = new PersonVo();
        personVo.setName(null == name ? "" : personVo.getName());
        personVo.setAge(null == age ? 0 : age);
        return personVo;
    }

    @RequestMapping(value = "/getPersonByName_Post", method = {RequestMethod.POST})
    @ResponseBody
    public PersonVo getPersonByName_Post(@RequestBody(required = false) PersonVo person) {
        PersonVo personVo = new PersonVo();
        if (null != person) {
            personVo.setName(null == person.getName() ? "" : person.getName());
            personVo.setAge(person.getAge());
        }
        return personVo;
    }
}
