package com.sun.xiaotian.demo.hystrix.web;


import com.sun.xiaotian.demo.hystrix.base.ResultT;
import com.sun.xiaotian.demo.hystrix.hystrix.CommandHelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("helloWorld")
public class HelloWorldController {

    private CommandHelloWorld commandHelloWorld;

    @GetMapping("say")
    public ResultT say() {
        commandHelloWorld = new CommandHelloWorld("Hystrix");
        return ResultT.ofData(commandHelloWorld.execute());
    }
}
