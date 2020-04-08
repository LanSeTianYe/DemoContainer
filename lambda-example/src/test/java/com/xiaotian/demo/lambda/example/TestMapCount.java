package com.xiaotian.demo.lambda.example;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMapCount {

    @Test
    public void test() {
        Map<Integer, Map<Integer, Map<Integer, List<String>>>> result = Stream.of("a", "b", "c", "aa", "bb", "ccc")
                .collect(Collectors.groupingBy(String::length, Collectors.groupingBy(String::hashCode, Collectors.groupingBy(String::length))));
        System.out.println(result);
    }
}
