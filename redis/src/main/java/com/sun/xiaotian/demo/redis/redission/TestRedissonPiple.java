package com.sun.xiaotian.demo.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RScoredSortedSetAsync;
import org.redisson.api.RedissonClient;


public class TestRedissonPiple {

    private static final RedissonClient redissonClient = Redisson.create();
    private static final String key = "test_batch_";

    public static void main(String[] args) {
        RBatch batch = redissonClient.createBatch();
        for (int i = 0; i < 100; i++) {
            RScoredSortedSetAsync<Object> scoredSortedSet = batch.getScoredSortedSet(key + i);
            scoredSortedSet.addAsync(((double) System.currentTimeMillis()), key + i);
        }
        BatchResult<Boolean> execute = (BatchResult<Boolean>) batch.execute();
        System.out.println(execute.getResponses());
        redissonClient.shutdown();
    }
}
