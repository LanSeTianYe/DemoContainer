package com.xiaotian.demo.test;

import java.util.HashMap;
import java.util.HashSet;

public class Test {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> integerSet = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            map.put(i, i * 2);
            integerSet.add(i);
            integerSet.add(i);
        }

        map.forEach((k, v) -> System.out.printf("key:%d, value:%d\n", k, v));

        for (Integer value : integerSet) {
            System.out.println(value);
        }
    }
}



