package com.sun.xiaotian.demo.redis.redis_cli;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动数量控制
 */
public class ActivityCountControl {

    private static final JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);

    private static final String activityKeyPrefix = "activity_";

    /**
     * 活动是否超频缓存
     */
    private static final Map<String, Boolean> overLimitMap = new HashMap<>();


    /**
     * 开始活动
     *
     * @param activityName 活动名称
     * @param controlCount 控制数量
     * @return
     */
    public boolean startActivity(String activityName, Integer controlCount) {
        overLimitMap.put(activityName, false);
        Jedis redis = jedisPool.getResource();
        String setResult = redis.set(getKey(activityName), String.valueOf(controlCount));
        redis.close();
        return null != setResult && "OK".equalsIgnoreCase(setResult);
    }

    /**
     * 参与活动
     *
     * @param activityName 活动名称
     * @return
     */
    public boolean participateActivity(String activityName) {
        if (overLimitMap.getOrDefault(activityName, false)) {
            return true;
        }
        Jedis redis = jedisPool.getResource();
        long remainingCount = redis.decr(getKey(activityName));
        redis.close();
        return remainingCount >= 0;
    }

    private String getKey(String activityName) {
        return activityKeyPrefix + activityName.toLowerCase();
    }
}
