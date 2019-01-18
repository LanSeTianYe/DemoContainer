package com.xiaotian.demo.test.util;


import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TimeStatisticsUtil {

    private static final List<Task> taskList = new ArrayList<>();

    private static Task currTask;

    private static Consumer<String> consumer = System.out::println;

    public static void startTask(String name) {
        consumer.accept("start task : " + name);
        currTask = new Task(name);
        currTask.start();
        taskList.add(currTask);

    }

    public static void endTask() {
        currTask.end();
        consumer.accept("end task : " + currTask.name);
        consumer.accept(currTask.toString());
        currTask = null;
    }

    public void setConsumer(Consumer<String> consumer) {
        this.consumer = consumer;
    }

    public static void showResult() {
        consumer.accept("");
        consumer.accept("");
        consumer.accept("final result: ---------------------------------------");
        taskList.forEach(task -> consumer.accept(task.toString()));
        consumer.accept("final result: ---------------------------------------");
    }

    private static class Task {

        private String name;

        private LocalTime startTime;

        private LocalTime endTime;

        private Task(String name) {
            this.name = name;
        }

        public void start() {
            startTime = LocalTime.now();
        }

        public void end() {
            endTime = LocalTime.now();
        }

        @Override
        public String toString() {
            return String.format("TaskName: %s, startTime: %s, endTime: %s, costTime: %s ", name, startTime.getNano() / 1000, endTime.getNano() / 1000, Duration.between(startTime, endTime).toMillis());
        }
    }
}
