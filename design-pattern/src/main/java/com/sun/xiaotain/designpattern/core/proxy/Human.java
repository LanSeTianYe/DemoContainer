package com.sun.xiaotain.designpattern.core.proxy;

public class Human implements DoSomething {

    @Override
    public String doSomething() {
        String helloWorld = "Hello World!";
        System.out.println(helloWorld);
        return helloWorld;
    }
}
