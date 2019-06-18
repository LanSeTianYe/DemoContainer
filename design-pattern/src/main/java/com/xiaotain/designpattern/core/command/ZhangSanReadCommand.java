package com.xiaotain.designpattern.core.command;

public class ZhangSanReadCommand extends Command {

    private Student student;

    public ZhangSanReadCommand() {
        this.student = new ZhangSan();
    }

    @Override
    void execute() {
        student.read();
    }
}
