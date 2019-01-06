package com.sun.xiaotian.demo.test.rmi;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * JAVA RMI 实现 UUID 服务器
 */
public interface UUIDInterface extends Remote, Serializable {
    long get();
}
