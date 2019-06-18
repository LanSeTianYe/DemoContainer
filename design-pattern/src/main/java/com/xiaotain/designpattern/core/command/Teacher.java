package com.xiaotain.designpattern.core.command;

public class Teacher {

    public static void main(String[] args) {

        Command command = new AllStudentReadCommand();
        CommandInvoker.invoke(command);

        command = new ZhangSanReadCommand();
        CommandInvoker.invoke(command);
    }
}
