package com.xiaotian.demo.test.concurrent.util;


import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class TestAtomicReferenceFieldUpdater {

    private volatile Node node;

    private static final AtomicReferenceFieldUpdater<TestAtomicReferenceFieldUpdater, Node> leftUpdater =
            AtomicReferenceFieldUpdater.newUpdater(TestAtomicReferenceFieldUpdater.class, Node.class, "node");


    public static void main(String[] args) {
        Node node = new Node(10);
        TestAtomicReferenceFieldUpdater testAtomicReferenceFieldUpdater = new TestAtomicReferenceFieldUpdater();
        leftUpdater.compareAndSet(testAtomicReferenceFieldUpdater, null, node);
        leftUpdater.compareAndSet(testAtomicReferenceFieldUpdater, node, new Node(20));
        System.out.println(leftUpdater.get(testAtomicReferenceFieldUpdater).value);
    }

    static class Node {
        private final int value;
        Node(int value) {
            this.value = value;
        }
    }
}


