package com.sun.xiaotain.designpattern.core.decorator;

public class BeforeDecorator2 extends BeforeDecorator {

    public BeforeDecorator2(Handler handler) {
        super(handler);
    }

    @Override
    public void before() {
        System.out.println("before2 ... ...");
    }
}
