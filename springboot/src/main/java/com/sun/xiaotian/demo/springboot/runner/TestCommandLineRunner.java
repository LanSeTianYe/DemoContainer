package com.sun.xiaotian.demo.springboot.runner;

import com.sun.xiaotian.demo.springboot.async.TestAsyncMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class TestCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TestAsyncMethod testAsyncMethod;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("TestCommandLineRunner start");
        testAsyncMethod.run();
        System.out.println("TestCommandLineRunner end");
    }
}
