package com.xiaotian.demo.test.queue;

import java.util.PriorityQueue;
import java.util.Random;

public class TestPriorityQueue {

    private final static Random random = new Random(37);

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 100; i++) {
            priorityQueue.add(random.nextInt(100));
        }
        System.out.println(priorityQueue.size());

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }
    }
}
