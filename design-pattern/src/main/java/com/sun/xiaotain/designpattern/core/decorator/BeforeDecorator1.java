package com.sun.xiaotain.designpattern.core.decorator;

public class BeforeDecorator1 extends BeforeDecorator  {

    public BeforeDecorator1(Handler handler) {
        super(handler);
    }

    @Override
    public void before() {
        System.out.println("before1 ... ...");
    }
}
