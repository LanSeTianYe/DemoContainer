package com.sun.xiaotian.demo.hystrix;

import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.hystrix.*;


public class CommandFacadeWithPrimarySecondary extends HystrixCommand<String> {

    private final int id;

    private final static DynamicBooleanProperty usePrimary = DynamicPropertyFactory.getInstance().getBooleanProperty("primarySecondary.usePrimary", true);

    public CommandFacadeWithPrimarySecondary(int id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX")).andCommandKey(HystrixCommandKey.Factory.asKey("PrimarySecondaryCommand")).andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)));
        this.id = id;
    }

    @Override
    protected String run() {
        if (usePrimary.get()) {
            return new PrimaryCommand(id).execute();
        } else {
            return new SecondaryCommand(id).execute();
        }
    }

    @Override
    protected String getFallback() {
        return "static-fallback-" + id;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    private static class PrimaryCommand extends HystrixCommand<String> {

        private final int id;

        private PrimaryCommand(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX")).andCommandKey(HystrixCommandKey.Factory.asKey("PrimaryCommand")).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PrimaryCommand")).andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(600)));
            this.id = id;
        }

        @Override
        protected String run() {
            return "responseFromPrimary-" + id;
        }

    }

    private static class SecondaryCommand extends HystrixCommand<String> {

        private final int id;

        private SecondaryCommand(int id) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX")).andCommandKey(HystrixCommandKey.Factory.asKey("SecondaryCommand")).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SecondaryCommand")).andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(100)));
            this.id = id;
        }

        @Override
        protected String run() {
            return "responseFromSecondary-" + id;
        }
    }
}
