package com.xiaotian.demo.rabbitmq.core.util;

import org.slf4j.Logger;

public class LogUtil {

    public static void logException(Logger logger, Exception e) {
        logger.error(e.getMessage(), e);
    }
}
