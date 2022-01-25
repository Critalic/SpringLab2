package com.example.springlab2.dao;

import com.example.springlab2.model.Currency;
import com.example.springlab2.model.CurrencyName;
import com.example.springlab2.model.RateByDate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Repository("repository")
public class MainDao {
    private final ArrayList<RateByDate> rateByDates;

    public MainDao(ArrayList<RateByDate> currencies) {
        this.rateByDates = currencies;

        ArrayList<Currency> currencies1 = new ArrayList<>();
        currencies1.add(new Currency(CurrencyName.US.toString(), 28.7));
        currencies1.add(new Currency(CurrencyName.EUR.toString(), 32.7));
        currencies1.add(new Currency(CurrencyName.UK.toString(), 38.7));

        ArrayList<Currency> currencies2 = new ArrayList<>();
        currencies2.add(new Currency(CurrencyName.US.toString(), 27.7));
        currencies2.add(new Currency(CurrencyName.EUR.toString(), 31.7));
        currencies2.add(new Currency(CurrencyName.UK.toString(), 37.7));

        this.rateByDates.add(new RateByDate(
                new GregorianCalendar(2022, Calendar.JANUARY, 10), currencies1));
        this.rateByDates.add(new RateByDate(
                new GregorianCalendar(2022, Calendar.JANUARY, 23), currencies2));
    }

    public ArrayList<RateByDate> getRates() {
        return rateByDates;
    }

    public synchronized void addRate(RateByDate rate) {
        this.rateByDates.add(rate);
    }

    public synchronized void addCurrency(Currency currency, Calendar date) {
        RateByDate rateToEdit = rateByDates.stream()
                .filter(rate -> rate.getDate().equals(date))
                .findFirst().orElse(new RateByDate(date, new ArrayList<>()));
        rateToEdit.addCurrency(currency);
        rateByDates.removeIf(rate -> rate.getDate().equals(rateToEdit.getDate()));
        rateByDates.add(rateToEdit);
    }
    public synchronized void deleteCurrency(String currencyName, Calendar date) {
        RateByDate rateToEdit = rateByDates.stream()
                .filter(rate -> rate.getDate().equals(date))
                .findFirst().orElse(null);
        rateToEdit.removeCurrency(currencyName);
        rateByDates.removeIf(rate -> rate.getDate().equals(rateToEdit.getDate()));
        rateByDates.add(rateToEdit);
    }
}
