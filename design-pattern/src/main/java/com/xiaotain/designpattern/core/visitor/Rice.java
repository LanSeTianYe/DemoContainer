package com.xiaotain.designpattern.core.visitor;

public class Rice implements Food {

    @Override
    public String getName() {
        return "Rice";
    }

    @Override
    public int getCount() {
        return 200;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
