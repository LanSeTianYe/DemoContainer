package com.sun.xiaotian.demo.springboot.demo.config;

import com.sun.xiaotian.demo.springboot.demo.interpecter.MyIntercepter;
import com.sun.xiaotian.demo.springboot.demo.resolver.PersonParameterHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class AppConfig implements WebMvcConfigurer {

    private final MyIntercepter filterIntercepter;

    private final PersonParameterHandlerMethodArgumentResolver personParameterHandlerMethodArgumentResolver;

    public AppConfig(MyIntercepter filterIntercepter, PersonParameterHandlerMethodArgumentResolver personParameterHandlerMethodArgumentResolver) {
        this.filterIntercepter = filterIntercepter;
        this.personParameterHandlerMethodArgumentResolver = personParameterHandlerMethodArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(filterIntercepter);

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(personParameterHandlerMethodArgumentResolver);
    }
}
