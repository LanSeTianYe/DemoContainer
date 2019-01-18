package com.xiaotain.designpattern.core.decorator;

public class AfterDecorator1 extends AfterDecorator {

    public AfterDecorator1(Handler handler) {
        super(handler);
    }

    @Override
    public void after() {
        System.out.println("after2  ... ...");
    }
}
