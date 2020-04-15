package com.sun.xiaotian.demo.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        
        Flowable.just("Hello Rx Java!")
                .subscribe(System.out::println).dispose();

        Flowable<String> flowable = Flowable.just("a", "b", "c");

        Maybe<String> c = flowable.elementAt(2);
        System.out.println(c.blockingGet());

        Stream.of("Hello Stream!").forEach(System.out::println);

        String findValue = "c";
        Stream.of("a", "b", "c", "c", "d")
                .peek(System.out::println)
                .filter(findValue::equals)
                .findFirst()
                .ifPresent(System.out::println);

    }
}
