package com.sun.xiaotain.demo.springcloud.gateway.config;

import com.sun.xiaotain.demo.springcloud.gateway.filter.ratelimit.RateLimitConfig;
import com.sun.xiaotain.demo.springcloud.gateway.filter.ratelimit.StringRateLimit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class StringRateLimitConfig {

    @Bean
    public RateLimitConfig rateLimitConfig() {
        RateLimitConfig rateLimitConfig = new RateLimitConfig();
        rateLimitConfig.setInterval(TimeUnit.SECONDS.toMillis(10));
        rateLimitConfig.setAllowTimes(3);
        return rateLimitConfig;
    }

    @Bean
    public StringRateLimit stringRateLimit() {
        StringRateLimit stringRateLimit = new StringRateLimit();
        stringRateLimit.setRateLimitConfig(rateLimitConfig());
        return stringRateLimit;
    }
}
