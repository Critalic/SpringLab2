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
    private final ArrayList<RateByDate> currencies;

    public MainDao(ArrayList<RateByDate> currencies) {
        this.currencies = currencies;

        ArrayList<Currency> currencies1 = new ArrayList<>();
        currencies1.add(new Currency(CurrencyName.US, 28.7));
        currencies1.add(new Currency(CurrencyName.EUR, 32.7));
        currencies1.add(new Currency(CurrencyName.UK, 38.7));

        ArrayList<Currency> currencies2 = new ArrayList<>();
        currencies2.add(new Currency(CurrencyName.US, 27.7));
        currencies2.add(new Currency(CurrencyName.EUR, 31.7));
        currencies2.add(new Currency(CurrencyName.UK, 37.7));

        this.currencies.add(new RateByDate(
                new GregorianCalendar(2022, Calendar.JANUARY, 10), currencies1));
        this.currencies.add(new RateByDate(new GregorianCalendar(), currencies2));
    }

    public ArrayList<RateByDate> getRates() {
        return currencies;
    }

    public void addRate(RateByDate rate) {
        currencies.add(rate);
    }
}
