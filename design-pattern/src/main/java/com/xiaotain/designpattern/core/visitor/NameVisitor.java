package com.xiaotain.designpattern.core.visitor;

public class NameVisitor implements Visitor<String> {

    @Override
    public String visit(Food food) {
        return food.getName();
    }
}
