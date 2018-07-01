package com.sun.xiaotain.designpattern.core.decorator;

public class AfterDecorator2 extends AfterDecorator {

    public AfterDecorator2(Handler handler) {
        super(handler);
    }

    @Override
    public void after() {
        System.out.println("after1  ... ...");
    }
}
