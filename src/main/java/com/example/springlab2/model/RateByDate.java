package com.example.springlab2.model;

import java.util.ArrayList;
import java.util.Calendar;

public class RateByDate {
    private Calendar date;
    private ArrayList<Currency> currencies;

    public RateByDate() {
    }

    public RateByDate(Calendar date, ArrayList<Currency> currencies) {
        this.date = date;
        this.currencies = currencies;
    }

    public Calendar getDate() {
        return date;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }
}
