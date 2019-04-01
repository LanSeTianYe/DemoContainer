package com.xiaotian.demo.algorithm.leetcode;

public class A_622_DesignCircularQueue {

    private final int[] Dqueue;
    private final int size;
    private int currCount = 0;
    private int frontIndex, rearIndex = 0;

    public A_622_DesignCircularQueue(int k) {
        this.size = k;
        this.Dqueue = new int[k];
    }

    public boolean enQueue(int value) {
        if (currCount == size) {
            return false;
        } else {
            if (currCount == 0) {
                rearIndex = frontIndex = 0;
            } else {
                rearIndex = nextRearIndex();
            }
            Dqueue[rearIndex] = value;
            currCount++;
            return true;
        }
    }

    public boolean deQueue() {
        if (currCount == 0) {
            return false;
        } else {
            frontIndex = nextFront();
            currCount--;
            return true;
        }
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        } else {
            return Dqueue[frontIndex];
        }
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        } else {
            return Dqueue[rearIndex];
        }
    }

    public boolean isEmpty() {
        return currCount == 0;
    }

    public boolean isFull() {
        return currCount == size;
    }

    private int nextRearIndex() {
        return (rearIndex + 1) % size;
    }

    private int nextFront() {
        return (frontIndex + 1) % size;
    }
}
