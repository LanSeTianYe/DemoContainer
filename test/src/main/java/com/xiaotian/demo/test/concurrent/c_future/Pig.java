package com.xiaotian.demo.test.concurrent.c_future;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;


public class Pig {

    private static final SecureRandom secureRandom = new SecureRandom();

    private final String name;

    Pig(String name) {
        this.name = name;
    }

    void sleep(){
        int sleepTime = getNumber(2000);
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("pig_name: %s, ThreadId: %s, sleepTime: %s ", name, Thread.currentThread().getId(), sleepTime));
    }

    private int getNumber(int bound) {
        Integer result = 0;
        byte[] bytes = new byte[bound];
        secureRandom.nextBytes(bytes);
        for (int i = 0; i < bytes.length; i++) {
            if (result > Integer.MAX_VALUE - 1000L) {
                return result % bound;
            }
            result = result + Math.abs(((int) bytes[i]));
        }
        return result % bound;
    }

    @Override
    public String toString() {
        return "Pig{" + "name='" + name + '\'' + '}';
    }
}
