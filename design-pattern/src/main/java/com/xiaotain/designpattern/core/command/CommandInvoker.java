package com.xiaotain.designpattern.core.command;

public class CommandInvoker {

    public static void invoke(Command command) {
        command.execute();
    }
}
