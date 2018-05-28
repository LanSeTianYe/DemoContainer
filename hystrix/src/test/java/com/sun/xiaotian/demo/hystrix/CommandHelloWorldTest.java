package com.sun.xiaotian.demo.hystrix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CommandHelloWorldTest {

    private final static Logger logger = LogManager.getLogger(CommandHelloWorldTest.class);

    @Test
    void testAsync() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Future<String> xiaoTian = new CommandHelloWorld("XiaoTian").queue();
        Future<String> zhangSan = new CommandHelloWorld("ZhangSan").queue();
        Future<String> liSi = new CommandHelloWorld("LiSi").queue();

        logger.info("start testAsync");
        assertEquals("Hello XiaoTian !", xiaoTian.get());
        assertEquals("Hello ZhangSan !", zhangSan.get());
        assertEquals("Hello LiSi !", liSi.get());
        logger.info("end testAsync");
        long end = System.currentTimeMillis();
        long constTime = ((end - start) / 1000);
        logger.info("耗时:" + constTime);
        assertTrue(constTime < 2L);
    }

    @Test
    void testSync(){
        long start = System.currentTimeMillis();

        String xiaoTian = new CommandHelloWorld("XiaoTian").execute();
        String zhangSan = new CommandHelloWorld("ZhangSan").execute();
        String liSi = new CommandHelloWorld("LiSi").execute();

        assertEquals("Hello XiaoTian !", xiaoTian);
        assertEquals("Hello LiSi !", liSi);
        assertEquals("Hello ZhangSan !", zhangSan);

        long end = System.currentTimeMillis();
        long constTime = ((end - start) / 1000);
        logger.info("耗时:" + constTime);
        assertTrue(constTime >= 3L);
    }
}