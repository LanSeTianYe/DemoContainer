package com.sun.xiaotian.demo.hystrix;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CommandThatFailsFastTest {

    @Test
    void testSuccess() {
        assertTrue(new CommandThatFailsFast(false).execute());
    }

    @Test
    void testFailure() {
        try {
            new CommandThatFailsFast(true).execute();
            fail("we should have thrown an exception");
        } catch (HystrixRuntimeException e) {
            assertEquals("failure from CommandThatFailsFast", e.getCause().getMessage());
            e.printStackTrace();
        }
    }
}