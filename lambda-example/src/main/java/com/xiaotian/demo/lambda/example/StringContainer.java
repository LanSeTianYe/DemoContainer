package com.xiaotian.demo.lambda.example;

public class StringContainer {

    private final StringBuilder builder;
    private final String prefix;
    private final String suffix;
    private final String split;

    public StringContainer(String split, String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.split = split;
        this.builder = new StringBuilder();
    }

    public void add(String elements) {
        if (areAtStart()) {
            builder.append(prefix);
        } else {
            builder.append(split);
        }
        builder.append(elements);
    }

    public StringContainer merge(StringContainer other) {
        builder.append(other.builder);
        return this;
    }

    private boolean areAtStart() {
        return builder.length() == 0;
    }

    @Override
    public String toString() {
        return builder.append(suffix).toString();
    }
}
