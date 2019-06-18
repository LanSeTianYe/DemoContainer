package com.xiaotain.designpattern.core.command;

public abstract class Student {

    final String name;

    Student(String name) {
        this.name = name;
    }

    public abstract void read();
}
