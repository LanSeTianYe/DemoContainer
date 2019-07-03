package com.xiaotian.datasource.dynamic.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class MyCustomListener implements ServletContextListener, ServletRequestListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("context destroy ...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("request init ...");
    }
}
