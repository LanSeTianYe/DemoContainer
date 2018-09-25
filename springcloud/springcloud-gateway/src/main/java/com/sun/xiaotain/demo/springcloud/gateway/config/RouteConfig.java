package com.sun.xiaotain.demo.springcloud.gateway.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(-1)
public class RouteConfig {

    private String uri = "http://www.sunfeilong.com:8080";

    private static final Logger logger = LogManager.getLogger(RouteConfig.class);

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(predicate -> predicate.host("www.sunfeilong.*")
                        .filters(filter -> filter.addRequestHeader("source", "springcloud gateway"))
                        .uri(uri)
                )
                .build();

    }

}
