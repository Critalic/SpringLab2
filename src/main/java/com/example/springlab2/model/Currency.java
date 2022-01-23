package com.example.springlab2.model;

public class Currency {
    private final CurrencyName currency;
    private final double courseToUAH;

    public Currency(CurrencyName currency, double courseToUAH) {
        this.currency = currency;
        this.courseToUAH = courseToUAH;
    }

    public CurrencyName getCurrency() {
        return currency;
    }

    public double getCourseToUAH() {
        return courseToUAH;
    }
}


