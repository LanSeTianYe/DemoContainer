package com.xiaotian.demo.task.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

    private Data name;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(String.format("Hello %s !", name.getClass().getName()));
    }

    public Data getName() {
        return name;
    }

    public void setName(Data name) {
        this.name = name;
    }
}