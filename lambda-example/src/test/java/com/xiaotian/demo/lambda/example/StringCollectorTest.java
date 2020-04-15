package com.xiaotian.demo.lambda.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

public class StringCollectorTest {

    @Test
    public void test() {
        String result = Stream.of("a", "b", "c")
                .collect(new StringCollector(",", "[", "]"));
        Assert.assertEquals("[a,b,c]", result);
    }

}