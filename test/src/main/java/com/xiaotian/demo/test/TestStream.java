package com.xiaotian.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class TestStream {

    public static void main(String[] args) {
        List<String> result = map(Stream.of("a", "b", "c"), String::toUpperCase);
        System.out.println(result);

        result = filter(Stream.of("apple", "banana", "lemon", "watermelon", "orange", "grape"), v -> v.length() > 5);
        System.out.println(result);
    }

    private static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<>(),
                (acc, input) -> {
                    ArrayList<O> temp = new ArrayList<>(acc);
                    temp.add(mapper.apply(input));
                    return temp;
                }, (left, right) -> {
                    left.addAll(right);
                    return left;
                });
    }

    private static <I> List<I> filter(Stream<I> stream, Predicate<I> predicate) {
        return stream.reduce(new ArrayList<>(),
                (acc, input) -> {
                    ArrayList<I> temp = new ArrayList<>(acc);
                    if (predicate.test(input)) {
                        temp.add(input);
                    }
                    return temp;
                }, (left, right) -> {
                    left.addAll(right);
                    return left;
                });
    }

}
