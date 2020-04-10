package com.xiaotian.demo.lambda.example;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class GroupByCollector<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {

    private final static Set<Characteristics> characteristics = new HashSet<>();

    static {
        characteristics.add(Characteristics.IDENTITY_FINISH);
    }

    private final Function<? super T, ? extends K> classifier;

    public GroupByCollector(Function<? super T, ? extends K> classifier) {
        this.classifier = classifier;
    }

    @Override
    public Supplier<Map<K, List<T>>> supplier() {
        return HashMap::new;
    }

    @Override
    public BiConsumer<Map<K, List<T>>, T> accumulator() {
        return (map, v) -> {
            K key = classifier.apply(v);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(v);
        };
    }

    @Override
    public BinaryOperator<Map<K, List<T>>> combiner() {
        return (a, b) -> {
            a.putAll(b);
            return a;
        };
    }

    @Override
    public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
        return map -> map;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return characteristics;
    }

}
