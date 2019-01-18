package com.xiaotain.designpattern.core.decorator;


import java.util.Random;

public class TestDecorator {

    private static final Random random = new Random(34);

    public static void main(String[] args) {

        Handler handler = new RequestHandler();

        for (int i = 0; i < 100; i++) {
            int nextInt = random.nextInt(10000);
            if(nextInt % 4 == 0) {
                handler = new AfterDecorator1(handler);
            }
            if(nextInt % 4 == 1) {
                handler = new AfterDecorator2(handler);
            }
            if(nextInt % 4 == 2) {
                handler = new BeforeDecorator1(handler);
            }
            if(nextInt % 4 == 3) {
                handler = new BeforeDecorator2(handler);
            }
        }
        handler.execute();
    }
}
