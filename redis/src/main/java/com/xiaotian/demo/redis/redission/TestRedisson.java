package com.xiaotian.demo.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class TestRedisson {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        redissonClient.getBucket("name").set("123");
        String name = (String) redissonClient.getBucket("name").get();
        System.out.println(name);
        System.exit(2);
    }
}
