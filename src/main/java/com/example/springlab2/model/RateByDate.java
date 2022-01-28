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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    public void addCurrency(Currency currency) {
        this.currencies.add(currency);
    }
    public void removeCurrency(String currencyName) {
        this.currencies.removeIf(currency -> currency.getCurrency().equals(currencyName));
    }
}
