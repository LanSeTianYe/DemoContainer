package com.sun.xiaotain.demo.springcloud.gateway.ratelimit;

public interface RateLimit<T> {

    boolean isMatch(Object T);

    boolean isOverLimit(T accessor);

    void setRateLimitConfig(RateLimitConfig rateLimitConfig);
}
