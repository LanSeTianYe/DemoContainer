package com.xiaotain.designpattern.core.visitor;

public interface Food {

    String getName();

    int getCount();

    void accept(Visitor visitor);
}
