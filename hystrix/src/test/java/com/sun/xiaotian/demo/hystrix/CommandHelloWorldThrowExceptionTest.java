package com.sun.xiaotian.demo.hystrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CommandHelloWorldThrowExceptionTest {

    @Test
    void testException() {
        new CommandHelloWorldThrowException("Test").execute();
        assertEquals("Hello Exception !", new CommandHelloWorldThrowException("Test").execute());
    }
}