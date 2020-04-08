package com.xiaotian.demo.lambda.example;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class StringCollector implements Collector<String, StringContainer, String> {

    private final String split;
    private final String prefix;
    private final String suffix;

    public StringCollector(String split, String prefix, String suffix) {
        this.split = split;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public Supplier<StringContainer> supplier() {
        return () -> new StringContainer(split, prefix, suffix);
    }

    @Override
    public BiConsumer<StringContainer, String> accumulator() {
        return StringContainer::add;
    }

    @Override
    public BinaryOperator<StringContainer> combiner() {
        return StringContainer::merge;
    }

    @Override
    public Function<StringContainer, String> finisher() {
        return StringContainer::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
