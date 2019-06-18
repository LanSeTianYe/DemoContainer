package com.xiaotain.designpattern.core.command;

import java.util.ArrayList;
import java.util.List;

public class AllStudentReadCommand extends Command {

    private List<Student> students = new ArrayList<>();

    public AllStudentReadCommand() {
        students.add(new ZhangSan());
        students.add(new LiSi());
    }

    @Override
    void execute() {
        for (Student student : students) {
            student.read();
        }
    }
}
