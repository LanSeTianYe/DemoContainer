package com.xiaotian.demo.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RedissonClient;

public class TestBatchKeyExists {

    private static final RedissonClient redissonClient = Redisson.create();
    private static final String key = "test_batch_";

    public static void main(String[] args) {
        try {
            redissonClient.getBucket("1").set(1);
            redissonClient.getBucket("3").set(1);
            redissonClient.getBucket("5").set(1);
            redissonClient.getBucket("9").set(1);
            RBatch batch = redissonClient.createBatch();
            for (int i = 0; i < 100; i++) {
                batch.getBucket("" + i).isExistsAsync();
            }
            BatchResult<?> batchResult = batch.execute();
            System.out.println(batchResult.getResponses());
        }finally {
            redissonClient.shutdown();
        }
    }
}
