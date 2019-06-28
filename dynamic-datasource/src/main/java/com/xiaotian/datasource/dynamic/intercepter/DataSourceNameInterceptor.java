package com.xiaotian.datasource.dynamic.intercepter;

import com.xiaotian.datasource.dynamic.constant.Constant;
import com.xiaotian.datasource.dynamic.datasource.ThreadDataSourceNameHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class DataSourceNameInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String datasourceName = request.getParameter(Constant.DATASOURCE_NAME);
        if (StringUtils.isEmpty(datasourceName)) {
            log.error("request not contains datasource name");
            return false;
        }
        log.info("read datasource name from request {}", datasourceName);
        ThreadDataSourceNameHolder.set(datasourceName);
        return true;
    }
}
