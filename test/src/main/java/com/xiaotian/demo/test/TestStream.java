package com.xiaotian.demo.test;

import java.time.Clock;
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
        long start1 = Clock.systemUTC().millis();
        Optional<Integer> reduce = numbers.stream().reduce(Integer::sum);
        System.out.println(reduce);
        long end1 = Clock.systemUTC().millis();
        System.out.println("cost time: " + (end1 - start1));

        long start = Clock.systemUTC().millis();
        Optional<Integer> reduce2 = numbers.parallelStream().reduce(Integer::sum);
        long end = Clock.systemUTC().millis();
        System.out.println(reduce2);
        System.out.println("cost time: " + (end - start));
    }

}
