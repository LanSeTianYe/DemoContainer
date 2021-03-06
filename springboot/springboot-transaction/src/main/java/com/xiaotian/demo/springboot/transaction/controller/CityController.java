package com.xiaotian.demo.springboot.transaction.controller;

import com.xiaotian.demo.springboot.transaction.biz.CityBiz;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    private final CityBiz cityBiz;

    public CityController(CityBiz cityBiz) {
        this.cityBiz = cityBiz;
    }

    @GetMapping("/city/noException")
    public void noException() {
        cityBiz.addUserAndNoException();
    }

    @GetMapping("/city/exceptionInBefore")
    public void exceptionInBefore() {
        cityBiz.addUserAndExceptionBefore();
    }

    @GetMapping("/city/exceptionInMiddle")
    public void exceptionInMiddle() {
        cityBiz.addUserAndExceptionInMiddle();
    }

    @GetMapping("/city/exceptionInAfter")
    public void exceptionInAfter() {
        cityBiz.addUserAndExceptionAfter();
    }

    @GetMapping("/city/useThisInvokeTransactionMethod")
    public void useThisInvokeTransactionMethod() {
        cityBiz.useThisInvokeTransactionMethod();
    }
}
