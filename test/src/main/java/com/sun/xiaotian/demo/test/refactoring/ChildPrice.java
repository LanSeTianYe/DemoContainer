package com.sun.xiaotian.demo.test.refactoring;

public class ChildPrice extends Price {

    @Override
    public double getPrice(int daysRented) {
        return daysRented <= 3 ? 1.5 : 1.5 + (daysRented - 3) * 1.5;
    }
}
