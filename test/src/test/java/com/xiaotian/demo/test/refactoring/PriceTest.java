package com.xiaotian.demo.test.refactoring;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class PriceTest {

    @Test(expected = InterruptedException.class, timeout = 100)
    public void testTimeout() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            TimeUnit.MILLISECONDS.sleep(1);
        }
        throw new InterruptedException();
    }
}