package com.xiaotian.demo.algorithm.genetic;

public class ExpressionNode extends Node {

    private final Function function;
    private final Node[] children;

    public ExpressionNode(FunctionWrapper functionWrapper, Node[] children) {
        super(functionWrapper.getName());
        this.function = functionWrapper.getFunction();
        this.children = children;
    }

    public int execute(int[] params) {
        int[] results = new int[this.children.length];
        for (int i = 0; i < children.length; i++) {
            results[i] = children[i].execute(params);
        }
        return function.execute(results);
    }

    public Function getFunction() {
        return function;
    }

    public Node[] getChildren() {
        return children;
    }
}
