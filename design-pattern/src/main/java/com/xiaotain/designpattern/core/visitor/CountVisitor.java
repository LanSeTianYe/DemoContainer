package com.xiaotain.designpattern.core.visitor;

public class CountVisitor implements Visitor<String> {

    @Override
    public String visit(Food food) {
        return String.format("[name: %s, count:%s]", food.getName(), food.getCount());
    }
}
