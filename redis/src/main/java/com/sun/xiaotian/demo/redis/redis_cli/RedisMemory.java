package com.sun.xiaotian.demo.redis.redis_cli;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisMemory {
    private static final JedisPool jedisPool = new JedisPool("192.168.0.201", 6379);

    public static void main(String[] args) {
        try (Jedis redis = jedisPool.getResource()) {
            int number = 0;
            while (true) {
                redis.set("key" + number, String.valueOf(number));
                number++;
            }

        }
    }
}
