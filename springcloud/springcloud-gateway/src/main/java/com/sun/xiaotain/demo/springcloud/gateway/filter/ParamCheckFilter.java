package com.sun.xiaotain.demo.springcloud.gateway.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Order(-4)
@ConditionalOnProperty(name = "switch.param.check", havingValue = "true")
public class ParamCheckFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (containsToken(exchange) && containsHostHeader(exchange)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }
    }

    private boolean containsToken(ServerWebExchange exchange) {
        List<String> tokenList = exchange.getRequest().getQueryParams().get("token");
        return tokenList != null && tokenList.size() > 0;
    }

    private boolean containsHostHeader(ServerWebExchange exchange) {
        List<String> host = exchange.getRequest().getHeaders().get("host");
        return host != null && host.size() > 0;
    }
}
