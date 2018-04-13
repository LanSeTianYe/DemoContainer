package com.sun.xiaotian.demo.test.refactoring;


public class RegularPrice extends Price {
    @Override
    public double getPrice(int daysRented) {
        return daysRented <= 2 ? 2 : 2 + (daysRented - 2);
    }
}
