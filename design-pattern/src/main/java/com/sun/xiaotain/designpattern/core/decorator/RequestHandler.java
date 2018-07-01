package com.sun.xiaotain.designpattern.core.decorator;


public class RequestHandler implements Handler {

    @Override
    public void execute() {
        System.out.println("execute ... ...");
    }
}
