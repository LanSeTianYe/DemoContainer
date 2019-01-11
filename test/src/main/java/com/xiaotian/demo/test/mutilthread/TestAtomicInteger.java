package com.xiaotian.demo.test.mutilthread;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicInteger {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        ImmutableList<Integer> immutableList = ImmutableList.of(1, 2, 3, 4, 5, 6);
        immutableList.forEach(v -> {
            atomicInteger.addAndGet(1);
        });

        System.out.println(atomicInteger);
    }
}
