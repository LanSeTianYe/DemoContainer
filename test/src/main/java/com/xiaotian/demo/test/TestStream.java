package com.xiaotian.demo.test;

import java.util.Arrays;
import java.util.List;

public class TestStream {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
    
}
