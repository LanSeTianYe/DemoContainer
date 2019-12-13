package com.xiaotian.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TestStream {

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10000000; i++) {
            numbers.add(random.nextInt(10000));
        }

        //所有元素的和
        long start1 = System.currentTimeMillis();
        Optional<Integer> reduce = numbers.stream().reduce(Integer::sum);
        System.out.println(reduce);
        long end1 = System.currentTimeMillis();
        System.out.println("cost time: " + (end1 - start1));

        long start = System.currentTimeMillis();
        Optional<Integer> reduce2 = numbers.parallelStream().reduce(Integer::sum);
        long end = System.currentTimeMillis();
        System.out.println(reduce2);
        System.out.println("cost time: " + (end - start));
    }

}
