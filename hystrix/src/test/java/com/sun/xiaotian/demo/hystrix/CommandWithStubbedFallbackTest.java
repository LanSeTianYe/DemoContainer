package com.sun.xiaotian.demo.hystrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CommandWithStubbedFallbackTest {

    @Test
    void testWithStubbed() {
        CommandWithStubbedFallback command = new CommandWithStubbedFallback(1234, "ca");
        CommandWithStubbedFallback.UserAccount account = command.execute();
        assertTrue(command.isFailedExecution());
        assertTrue(command.isResponseFromFallback());
        assertEquals(1234, account.getCustomerId());
        assertEquals("ca", account.getCountryCode());
        assertEquals(true, account.isFeatureXPermitted());
        assertEquals(true, account.isFeatureXPermitted());
        assertEquals(false, account.isFeatureZPermitted());
    }
}