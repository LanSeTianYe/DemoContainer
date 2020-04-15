package com.xiaotian.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

    public static void main(String[] args) {

        List<String> result = map(Stream.of(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"
        ).parallel(), String::toUpperCase);
        System.out.println(result);

        result = filter(Stream.of(
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach",
                "apple", "banana", "lemon", "watermelon", "orange", "grape", "pineapple", "strawberry", "peach"
        ), v -> v.length() > 5);
        System.out.println(result);

        Map<Integer, Long> countMap = result.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(countMap);


        Integer reduce = Stream.of(1, 2, 3, 4).reduce(100, (x, y) -> x + y);
        System.out.println(reduce);
    }

    private static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<>(),
                (acc, input) -> {
                    ArrayList<O> temp = new ArrayList<>(acc);
                    temp.add(mapper.apply(input));
                    return temp;
                }, (left, right) -> {
                    ArrayList<O> temp = new ArrayList<>(left);
                    temp.addAll(right);
                    return temp;
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
                    ArrayList<I> temp = new ArrayList<>(left);
                    temp.addAll(right);
                    return temp;
                });
    }

}
