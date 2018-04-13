package com.sun.xiaotian.demo.test;

import java.util.Optional;

public class TestOptional {

    private static Optional<String> getValue() {
        return Optional.of("123");
    }

    private static Optional<String> getNullValue() {
        return Optional.ofNullable(null);
    }

    public static void main(String[] args) {
        if (getValue().isPresent()) {
            System.out.println(getValue().get());
        }

        Optional<String> filterResult = getValue().filter((o) -> o.contains("n"));
        System.out.println(filterResult);

        Optional<Double> doubleOptional = filterResult.map(Double::valueOf);
        System.out.println(doubleOptional.orElse(0D));

        System.out.println(getNullValue().isPresent());
        System.out.println(getNullValue().orElse("value is null"));
    }
}
