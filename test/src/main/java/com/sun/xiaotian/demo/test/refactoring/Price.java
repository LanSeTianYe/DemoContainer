package com.sun.xiaotian.demo.test.refactoring;


public abstract class Price {

    public abstract double getPrice(int daysRented);

    public int getFrequentRenterPoints(int dayRented){
        return 1;
    }
}
