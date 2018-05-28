package com.sun.xiaotian.demo.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class CommandHelloWorld extends HystrixCommand<String> {

    private final static Logger logger = LogManager.getLogger(CommandHelloWorld.class);

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"), 2000);
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        logger.info("run " + name);
        TimeUnit.SECONDS.sleep(1);
        return "Hello " + name + " !";
    }
}
