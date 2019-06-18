package com.xiaotain.designpattern.core.command;

public class ZhangSan extends Student {

    public ZhangSan() {
        super("张三");
    }

    @Override
    public void read() {
        System.out.println(String.format("%s 正在读书", name));
    }
}
