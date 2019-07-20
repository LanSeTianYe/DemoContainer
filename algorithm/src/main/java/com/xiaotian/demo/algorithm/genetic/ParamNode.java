package com.xiaotian.demo.algorithm.genetic;

public class ParamNode extends Node {

    private final int index;

    public ParamNode(int index) {
        super("param node");
        this.index = index;
    }

    public int execute(int[] params) {
        return params[index];
    }

    public int getIndex() {
        return index;
    }
}
