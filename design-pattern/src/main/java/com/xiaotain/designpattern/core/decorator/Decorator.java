package com.xiaotain.designpattern.core.decorator;


public abstract class Decorator implements Handler {

    protected Handler handler;

    public Decorator(Handler handler) {
        this.handler = handler;
    }
}
