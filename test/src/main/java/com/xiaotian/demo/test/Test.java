package com.xiaotian.demo.test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Test {

    public static void main(String[] args) {

        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(1);
        queue.add(2);
        Queue<Integer> queue2 = new ConcurrentLinkedQueue<>(queue);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        while (!queue2.isEmpty()) {
            System.out.println(queue2.poll());
        }
    }

}
