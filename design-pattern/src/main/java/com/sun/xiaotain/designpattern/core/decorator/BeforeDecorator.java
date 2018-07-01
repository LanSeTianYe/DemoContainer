package com.sun.xiaotain.designpattern.core.decorator;


public abstract class BeforeDecorator extends Decorator {

    public BeforeDecorator(Handler handler) {
        super(handler);
    }

    @Override
    public void execute() {
        before();
        handler.execute();
    }

    public abstract void before();
}
