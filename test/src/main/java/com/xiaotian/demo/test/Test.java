package com.xiaotian.demo.test;

public class Test {

    public static void main(String[] args) {

        Task task = new Task();
        System.out.println(task.getClass().getCanonicalName());
        System.out.println(task.getClass().getName());
        System.out.println(task.getClass().getTypeName());

    }

    static class Task implements Runnable {

        @Override
        public void run() {

        }
    }
}
