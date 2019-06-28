package com.xiaotian.datasource.dynamic.configuration;

import com.xiaotian.datasource.dynamic.intercepter.DataSourceNameInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DataSourceNameInterceptor());
    }
}
