package com.xiaotian.demo.test.refactoring;

public class Movie {

    public static final int CHILDREN = 2;
    public static final int REGULAR = 1;
    public static final int NEW_RELEASE = 0;

    private String title;

    private Price price;

    public Movie(String title, Price price) {
        this.title = title;
        this.price = price;
    }

    public double getPrice(int dayRented) {
        return price.getPrice(dayRented);
    }

    public String getTitle() {
        return title;
    }

    public int getFrequentRenterPoints(int dayRented) {
        return price.getFrequentRenterPoints(dayRented);
    }

    public void setPrice(int priceCode) {
        switch (priceCode) {
            case CHILDREN:
                this.price = new ChildPrice();
                break;
            case REGULAR:
                this.price = new RegularPrice();
                break;
            case NEW_RELEASE:
                this.price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
