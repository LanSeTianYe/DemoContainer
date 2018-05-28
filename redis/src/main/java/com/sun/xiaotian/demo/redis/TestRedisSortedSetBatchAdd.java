package com.sun.xiaotian.demo.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class TestRedisSortedSetBatchAdd {

    private static final Jedis jedis = new Jedis("127.0.0.1", 6379);

    private static final String key = "test_sortedset";

    private static int dataCount = 10000;

    public static void main(String[] args) {
        HashMap<String, Double> dataMap = new HashMap<>();
        for (int i = 0; i < dataCount; i++) {
            dataMap.put("" + i, Double.valueOf(i));
        }

        long start = System.currentTimeMillis();
        jedis.zadd(key, dataMap);
        System.out.println(System.currentTimeMillis() - start);
    }
}
