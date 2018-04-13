package com.sun.xiaotian.demo.test.refactoring;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class PriceTest {

    @Test(expected = InterruptedException.class, timeout = 100)
    public void testTimeout() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.MILLISECONDS.sleep(1);
        }
        throw new InterruptedException();
    }
}