package com.sun.xiaotain.demo.springcloud.gateway.filter.ratelimit;

public interface RateLimit<T> {

    boolean isMatch(Object T);

    boolean isOverLimit(T accessor);

    void setRateLimitConfig(RateLimitConfig rateLimitConfig);
}
