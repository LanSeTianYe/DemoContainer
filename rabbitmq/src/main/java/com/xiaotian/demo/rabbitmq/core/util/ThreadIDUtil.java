package com.xiaotian.demo.rabbitmq.core.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程ID工具
 *
 * @author sunfeilong [2020/9/4 10:35]
 */

public class ThreadIDUtil {

    private static final Map<Class, AtomicInteger> cache = new ConcurrentHashMap<>();

    public static String getTag(Class cls) {
        if (!cache.containsKey(cls)) {
            cache.put(cls, new AtomicInteger());
        }
        return cls.getSimpleName() + "-" + cache.get(cls).incrementAndGet();
    }


}
