package com.sun.xiaotian.demo.test.refactoring;


public class NewReleasePrice extends Price {
    @Override
    public double getPrice(int daysRented) {
        return 3 * daysRented;
    }

    @Override
    public int getFrequentRenterPoints(int dayRented) {
        return dayRented > 1 ? 2 : 1;
    }
}
