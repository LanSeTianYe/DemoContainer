package com.sun.xiaotian.demo.redis.redis_cli;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

public class DeleteKey {

    private static final JedisPool jedisPool = new JedisPool("192.168.0.201", 6379);

    public static void main(String[] args) throws IOException {
        try (Jedis redis = jedisPool.getResource()) {
            //删除数字
            for (int i = 0; i < 100; i++) {
                redis.del("" + i);
            }
        }
    }
}
