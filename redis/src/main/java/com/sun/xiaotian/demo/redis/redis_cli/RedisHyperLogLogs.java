package com.sun.xiaotian.demo.redis.redis_cli;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisHyperLogLogs {

    private static final JedisPool jedisPool = new JedisPool("192.168.0.201", 6379);

    public static void main(String[] args) {

        try (Jedis redis = jedisPool.getResource()) {
            String set_key = "consumer_set";
            String hll_key = "consumer_hll";
            int count = 10000;

            for (int i = 0; i < count; i++) {
                String consumer = "consumer_" + i;
                redis.sadd(set_key, consumer);
                redis.pfadd(hll_key, consumer);
                System.out.println("percent :");
            }

            System.out.println(redis.pfcount(hll_key));
            System.out.println(redis.scard(set_key));

        }
    }
}
