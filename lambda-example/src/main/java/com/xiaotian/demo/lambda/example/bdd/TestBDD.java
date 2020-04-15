package com.xiaotian.demo.lambda.example.bdd;

import org.junit.Assert;

public class TestBDD {

    public static void main(String[] args) {

        describe("测试BDD对象", description -> {
            description.should("测试为空的情况", expect -> {
                Assert.assertTrue(expect.that(null).isNull());
            });

            description.should("测试非空的情况", expect -> {
                Assert.assertTrue(expect.that(new BDD()).nonNull());
            });
        });
    }

    private static void describe(String description, Suite suite) {
        Description descriptionObj = new Description(description);
        suite.specifySuite(descriptionObj);
    }

}
