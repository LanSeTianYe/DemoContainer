package com.xiaotian.demo.test.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        for (int i = 0; i < 100; i++) {
            concurrentHashMap.put(i, i);
        }

        for (Map.Entry<Integer, Integer> keyValue : concurrentHashMap.entrySet()) {
            System.out.println(String.format("key: %s, value: %s", keyValue.getKey(), keyValue.getValue()));
        }

        System.out.println(1 >>> 3);
        System.out.println(-11 >>> 3);
    }
}
