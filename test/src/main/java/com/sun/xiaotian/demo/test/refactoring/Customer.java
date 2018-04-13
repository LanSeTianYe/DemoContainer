package com.sun.xiaotian.demo.test.refactoring;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;

    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        String result = "Rental for " + getName() + "\n";
        if (rentals.size() > 0) {
            for (Rental rental : rentals) {
                frequentRenterPoints += getFrequentRenterPoints(rental);
                result += rental.getMovie().getTitle() + "\t" + String.valueOf(getRentalAmount(rental)) + "\n";
                totalAmount += getRentalAmount(rental);
            }
            result += "Amount owned is " + String.valueOf(totalAmount) + "\n";
            result += "Your earned " + String.valueOf(frequentRenterPoints) + "frequent renter points";
        }
        return result;
    }

    private int getFrequentRenterPoints(Rental rental) {
        return rental.getMovie().getFrequentRenterPoints(rental.getDaysRented());
    }

    private double getRentalAmount(Rental rental) {
        return rental.getMovie().getPrice(rental.getDaysRented());
    }
}
