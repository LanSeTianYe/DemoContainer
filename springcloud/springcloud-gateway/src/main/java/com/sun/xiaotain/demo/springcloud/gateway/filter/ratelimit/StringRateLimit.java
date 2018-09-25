package com.sun.xiaotain.demo.springcloud.gateway.filter.ratelimit;

import java.util.*;

public class StringRateLimit implements RateLimit<String> {

    private RateLimitConfig rateLimitConfig;

    private Map<String, Queue<Date>> accessInfoMap = new HashMap<>();

    @Override
    public boolean isMatch(Object T) {
        return T instanceof String;
    }

    @Override
    public boolean isOverLimit(String accessor) {
        Queue<Date> accessQueue = accessInfoMap.getOrDefault(accessor, new ArrayDeque<>());
        increaseOne(accessor);
        if (accessQueue.size() <= rateLimitConfig.getAllowTimes()) {
            return false;
        } else {
            Date firstDate = null;
            while (accessQueue.size() > rateLimitConfig.getAllowTimes()) {
                firstDate = accessQueue.poll();
            }
            return firstDate == null || (System.currentTimeMillis() - firstDate.getTime() < rateLimitConfig.getInterval());
        }
    }

    private void increaseOne(String accessor) {
        Date accessDate = new Date();
        Queue<Date> accessDateQueue = accessInfoMap.getOrDefault(accessor, new ArrayDeque<>(rateLimitConfig.getAllowTimes()));
        accessDateQueue.offer(accessDate);
        accessInfoMap.put(accessor, accessDateQueue);
    }

    @Override
    public void setRateLimitConfig(RateLimitConfig rateLimitConfig) {
        this.rateLimitConfig = rateLimitConfig;
    }
}
