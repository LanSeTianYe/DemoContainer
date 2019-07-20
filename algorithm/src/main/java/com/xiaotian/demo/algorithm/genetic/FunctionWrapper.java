package com.xiaotian.demo.algorithm.genetic;

public class FunctionWrapper {

    private final Function function;
    private final String name;
    private final int childCount;

    public FunctionWrapper(Function function, String name, int childCount) {
        this.function = function;
        this.name = name;
        this.childCount = childCount;
    }

    public Function getFunction() {
        return function;
    }

    public String getName() {
        return name;
    }

    public int getChildCount() {
        return childCount;
    }
}
