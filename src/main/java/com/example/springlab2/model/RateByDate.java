package com.example.springlab2.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class RateByDate {
    private LocalDate date;
    private ArrayList<Currency> currencies;

    public RateByDate() {
    }

    public RateByDate(LocalDate date, ArrayList<Currency> currencies) {
        this.date = date;
        this.currencies = currencies;
    }

    public LocalDate getDate() {
        return date;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public RateByDate setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public RateByDate setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
        return this;
    }

    public void addCurrency(Currency currency) {
        this.currencies.add(currency);
    }

    public void removeCurrency(String currencyName) {
        this.currencies.removeIf(currency -> currency.getCurrency().equals(currencyName));
    }
}
