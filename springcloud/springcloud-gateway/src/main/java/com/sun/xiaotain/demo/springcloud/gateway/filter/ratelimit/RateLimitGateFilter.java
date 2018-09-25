package com.sun.xiaotain.demo.springcloud.gateway.filter.ratelimit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
//@Order(-2)
public class RateLimitGateFilter implements GlobalFilter {

    private static final Logger logger = LogManager.getLogger(RateLimitGateFilter.class);

    private final RateLimit<String> rateLimit;

    public RateLimitGateFilter(RateLimit rateLimit) {
        this.rateLimit = rateLimit;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = exchange.getRequest().getQueryParams().get("token").get(0);
        String path = exchange.getRequest().getPath().pathWithinApplication().value();
        if (token == null) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        } else if (rateLimit.isMatch(path)) {
            if (rateLimit.isOverLimit(token)) {
                logger.info(String.format("access over limit, token: %s, path: %s", token, path));
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            } else {
                return chain.filter(exchange);
            }
        }
        return exchange.getResponse().setComplete();
    }
}
