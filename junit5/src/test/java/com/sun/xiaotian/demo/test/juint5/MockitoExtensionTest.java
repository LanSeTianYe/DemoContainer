package com.sun.xiaotian.demo.test.juint5;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

@ExtendWith(TimingExtension.class)
class MockitoExtensionTest {

    @Test
    void extensionTest() throws InterruptedException {
        System.out.println("Hello 123!");
        TimeUnit.MILLISECONDS.sleep(500);
    }

    @Test
    void exceptionTest() {
        int number = 1 / 0;
    }

}
