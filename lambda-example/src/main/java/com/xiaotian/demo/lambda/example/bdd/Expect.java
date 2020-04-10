package com.xiaotian.demo.lambda.example.bdd;

/**
 * 期望
 */
public class Expect {

    private final String exceptDescription;

    public Expect(String exceptDescription) {
        this.exceptDescription = exceptDescription;
    }

    Adjust that(Object object) {
        return new Adjust(object);
    }
}
