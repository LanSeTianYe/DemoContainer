package com.xiaotain.designpattern.core.command;

public class LiSi extends Student {

    public LiSi() {
        super("李四");
    }

    @Override
    public void read() {
        System.out.println(String.format("%s 正在读书", name));
    }
}
