package com.xiaotain.designpattern.core.visitor;

public interface Visitor<T> {

    T visit(Food food);

}
