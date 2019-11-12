package com.sun.xiaotian.demo.springboot.demo.person;

import com.alibaba.fastjson.JSON;
import com.sun.xiaotian.demo.springboot.demo.common.HttpResult;
import com.sun.xiaotian.demo.springboot.demo.hystrix.GetAllUserHystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("person/")
@Scope("request")
public class PersonController {

    private final static Logger logger = LogManager.getLogger(PersonController.class);

    private final PersonService personService;
    private final GetAllUserHystrixCommand getAllUserHystrixCommand;

    public PersonController(PersonService personService, GetAllUserHystrixCommand getAllUserHystrixCommand) {
        this.personService = personService;
        this.getAllUserHystrixCommand = getAllUserHystrixCommand;
    }

    @RequestMapping("persons")
    public HttpResult getPersons(HttpSession session) {
        logger.info(String.format("%s_%s_invoke, session: %s", this.getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(), JSON.toJSONString(session)));
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

    @RequestMapping("testHttpRequest")
    public HttpResult testHttpRequest(HttpServletRequest request){
        logger.info(JSON.toJSONString(request.getSession()));
        return new HttpResult(true, JSON.toJSONString(request.getSession()));
    }
}
