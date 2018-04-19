package com.sun.xiaotian.demo.test.collection;

import java.util.ArrayList;
import java.util.List;

public class TestCollectionClear {

    private static final int arraySize = 10000000;

    public static void main(String[] args) {
        long startTIme = System.currentTimeMillis();
        List<Integer> first = new ArrayList<>(arraySize);
        List<Integer> second = new ArrayList<>(arraySize);
        List<Integer> third = new ArrayList<>(arraySize);
        List<Integer> fourth = new ArrayList<>(arraySize);
        List<Integer> fifth = new ArrayList<>(arraySize);
        initArray(arraySize, first);
        initArray(arraySize, second);
        initArray(arraySize, third);
        initArray(arraySize, fourth);
        initArray(arraySize, fifth);

        long initFinishedTime = System.currentTimeMillis();

        clear(first);
        clear(second);
        clear(third);
        clear(fourth);
        clear(fifth);
        long firstClearTime = System.currentTimeMillis();

        clear(first);
        clear(second);
        clear(third);
        clear(fourth);
        clear(fifth);
        long secondClearTime = System.currentTimeMillis();

        System.out.println(initFinishedTime - startTIme);
        System.out.println(firstClearTime - initFinishedTime);
        System.out.println(secondClearTime - firstClearTime);
    }


    private static void clear(List list) {
        list.clear();
    }

    private static void initArray(int arraySize, List<Integer> list) {
        for (int i = 0; i < arraySize; i++) {
            list.add(i);
        }
    }
}
