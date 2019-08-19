package com.xiaotian.demo.algorithm.genetic;

public abstract class Node {

    private final String name;

    public Node(String name) {
        this.name = name;
    }

    abstract int execute(int[] params);

    public String getName() {
        return name;
    }
}
