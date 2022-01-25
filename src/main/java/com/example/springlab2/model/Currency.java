package com.example.springlab2.model;

public class Currency {
    private final String currency;
    private final double courseToUAH;

    public Currency(String currency, double courseToUAH) {
        this.currency = currency;
        this.courseToUAH = courseToUAH;
    }

    public String getCurrency() {
        return currency;
    }

    public double getCourseToUAH() {
        return courseToUAH;
    }
}


