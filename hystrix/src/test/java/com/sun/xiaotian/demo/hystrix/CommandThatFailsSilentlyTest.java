package com.sun.xiaotian.demo.hystrix;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CommandThatFailsSilentlyTest {

    @Test
    public void testSuccess() {
        assertEquals(true, new CommandThatFailsSilently(false).execute());
    }

    @Test
    public void testFailure() {
        try {
            assertEquals(false, new CommandThatFailsSilently(true).execute());
        } catch (HystrixRuntimeException e) {
            fail("we should not get an exception as we fail silently with a fallback");
        }
    }
}