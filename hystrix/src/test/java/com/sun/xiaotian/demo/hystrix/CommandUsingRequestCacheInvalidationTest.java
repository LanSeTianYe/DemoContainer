package com.sun.xiaotian.demo.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CommandUsingRequestCacheInvalidationTest {

    @Test
    void test() {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            assertEquals("ValueBeforeSet_1", new CommandUsingRequestCacheInvalidation.GetterCommand(1).execute());
            CommandUsingRequestCacheInvalidation.GetterCommand commandAgainstCache = new CommandUsingRequestCacheInvalidation.GetterCommand(1);
            assertEquals("ValueBeforeSet_1", commandAgainstCache.execute());

            assertTrue(commandAgainstCache.isResponseFromCache());

            new CommandUsingRequestCacheInvalidation.SetterCommand(1, "ValueAfterSet_").execute();

            CommandUsingRequestCacheInvalidation.GetterCommand commandAfterSet = new CommandUsingRequestCacheInvalidation.GetterCommand(1);
            assertFalse(commandAfterSet.isResponseFromCache());

            CommandUsingRequestCacheInvalidation.GetterCommand commandAfterSetCache = new CommandUsingRequestCacheInvalidation.GetterCommand(1);
            assertFalse(commandAfterSetCache.isResponseFromCache());

            assertEquals("ValueAfterSet_1", commandAfterSet.execute());
        } finally {
            context.shutdown();
        }
    }
}