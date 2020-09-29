package com.xiaotain.designpattern.core.visitor;

public class Banana implements Food {

    @Override
    public String getName() {
        return "Banana";
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
