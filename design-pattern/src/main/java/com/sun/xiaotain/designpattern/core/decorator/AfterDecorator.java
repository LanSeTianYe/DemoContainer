package com.sun.xiaotain.designpattern.core.decorator;

public abstract class AfterDecorator extends Decorator {

    public AfterDecorator(Handler handler) {
        super(handler);
    }

    @Override
    public void execute() {
        handler.execute();
        after();
    }

    public abstract void after();

}
