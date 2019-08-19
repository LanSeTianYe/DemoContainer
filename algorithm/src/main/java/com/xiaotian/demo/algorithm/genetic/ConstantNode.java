package com.xiaotian.demo.algorithm.genetic;

public class ConstantNode extends Node {

    private final int value;

    public ConstantNode(int value) {
        super("constant node");
        this.value = value;
    }

    public int execute(int[] params) {
        return this.value;
    }

    public int getValue() {
        return value;
    }
}
