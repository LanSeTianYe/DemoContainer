package com.sun.xiaotian.zkdemo.constant;

import java.nio.charset.Charset;

/**
 * zookeeper 服务器连接信息
 */

public class ZookeeperConstant {

    /**
     * 集群服务器列表
     */
    public static final String HOSTS = "43.225.158.197:2181";

    /**
     * Session 过期时间，指定多长书时间客户端和服务器之间没有数据交互，连接就过期
     */
    public static final int SESSION_TIME = 5 * 1000;

    /**
     * 分布式配置信息路径
     */
    public static final String DISTRIBUTE_CONFIG_PATH = "/distribute_config";

    /**
     * UTF-8 编码
     */
    public static final String UTF_8 = "utf-8";
}
