package com.xiaotian.demo.redis.redission;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;

public class TestRedissonZSet {

    private static final RedissonClient redissonClient = Redisson.create();
    private static final String key = "test_batch_";

    public static void main(String[] args) {
        redissonClient.getScoredSortedSet(key + 1).add(2, 2);
        boolean contains = redissonClient.getScoredSortedSet(key + 1).contains(1);
        boolean contains1 = redissonClient.getScoredSortedSet(key + 1).contains(2);
        System.out.println(contains);
        System.out.println(contains1);
    }
}
