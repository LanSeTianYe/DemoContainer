package com.sun.xiaotian.demo.chatroom.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class NameGenerateUtilTest {

    @Test
    public void generate() throws InterruptedException {
        Set<String> nameSet = new HashSet<>();
        int repeatCount = 0;
        for (int i = 0; i < 10000; i++) {
            String name = NameGenerateUtil.generate();
            repeatCount = nameSet.add(name) ? repeatCount : repeatCount + 1;
            System.out.println(name);
        }
        System.out.println(String.format("repeat rate is: %s", (repeatCount * 1.0) / (repeatCount + nameSet.size())));
    }
}