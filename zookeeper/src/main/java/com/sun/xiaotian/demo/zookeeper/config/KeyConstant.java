package com.sun.xiaotian.demo.zookeeper.config;

/**
 * 配置信息的 key
 */

public enum KeyConstant {

    NAME("name"), AGE("age");

    private String text;

    KeyConstant(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
