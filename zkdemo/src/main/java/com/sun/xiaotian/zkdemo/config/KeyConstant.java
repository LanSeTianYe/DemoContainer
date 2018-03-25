package com.sun.xiaotian.zkdemo.config;

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
