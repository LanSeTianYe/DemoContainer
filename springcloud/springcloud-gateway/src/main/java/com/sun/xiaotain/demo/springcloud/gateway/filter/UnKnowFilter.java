package com.sun.xiaotain.demo.springcloud.gateway.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(100)
public class UnKnowFilter implements GlobalFilter {

    private static final Logger logger = LogManager.getLogger(UnKnowFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (StringUtils.isEmpty(exchange.getRequest().getHeaders().get("host"))) {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            logger.info(String.format("unKnow host path: %s", exchange.getRequest().getPath().pathWithinApplication().value()));
        } else {
            return chain.filter(exchange);
        }
        return exchange.getResponse().setComplete();
    }
}
