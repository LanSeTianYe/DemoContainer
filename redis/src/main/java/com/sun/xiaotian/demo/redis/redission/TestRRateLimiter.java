package com.sun.xiaotian.demo.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class TestRRateLimiter {

    private static final RedissonClient client = Redisson.create();

    public static void main(String[] args) throws InterruptedException {
        String key = "access_limit";
        for (int i = 0; i < 10; i++) {
            System.out.println(checkAccessFrequencyLimit(key, 10, 5));
            TimeUnit.SECONDS.sleep(1);
        }
        System.exit(3);
    }


    /**
     * 资源频率访问限制
     *
     * @param resource             要限制的资源（用于Redis的key）
     * @param timeInterval         时间间隔，单位:s
     * @param accessFrequency      在timeInterval时间内可以访问多少次
     *
     * @return 是否超过频率限制：true 没有 false超过
     */
    private static boolean checkAccessFrequencyLimit(String resource, int timeInterval, int accessFrequency) {
        long currAccessFrequency = client.getAtomicLong(resource).incrementAndGet();
        if(currAccessFrequency > accessFrequency) {
            return false;
        } else {
            if(client.getBucket(resource).remainTimeToLive() == -1) {
                client.getBucket(resource).expire(timeInterval, TimeUnit.SECONDS);
            }
        }
        return true;
    }
}
