package com.example.springlab2.dao;

import com.example.springlab2.configurations.MainConfig;
import com.example.springlab2.model.Currency;
import com.example.springlab2.model.CurrencyName;
import com.example.springlab2.model.RateByDate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Objects;

@Repository("repository")
public class MainDao {
    private final ArrayList<RateByDate> rateByDates;
    private final MainConfig config;

    public MainDao(ArrayList<RateByDate> currencies, MainConfig config) {
        this.rateByDates = currencies;
        this.config = config;

        ArrayList<Currency> currencies1 = new ArrayList<>();
        currencies1.add(new Currency(CurrencyName.US.toString(), 28.7));
        currencies1.add(new Currency(CurrencyName.EUR.toString(), 32.7));
        currencies1.add(new Currency(CurrencyName.UK.toString(), 38.7));

        ArrayList<Currency> currencies2 = new ArrayList<>();
        currencies2.add(new Currency(CurrencyName.US.toString(), 27.7));
        currencies2.add(new Currency(CurrencyName.EUR.toString(), 31.7));
        currencies2.add(new Currency(CurrencyName.UK.toString(), 37.7));

        this.rateByDates.add(config.rateByDate()
                .setDate(LocalDate.of(2022, Month.JANUARY, 10))
                .setCurrencies(currencies1));
        this.rateByDates.add(config.rateByDate()
                .setDate(LocalDate.of(2022, Month.JANUARY, 23))
                .setCurrencies(currencies2));
    }

    public ArrayList<RateByDate> getRates() {
        return rateByDates;
    }

    public synchronized void addRate(RateByDate rate) {
        this.rateByDates.add(rate);
    }

    public synchronized void addCurrency(Currency currency, LocalDate date) {
        RateByDate rateToEdit = rateByDates.stream()
                .filter(rate -> rate.getDate().isEqual(date))
                .findFirst().orElse(config.rateByDate()
                        .setDate(date));
        rateToEdit.addCurrency(currency);
        rateByDates.removeIf(rate -> rate.getDate().isEqual(rateToEdit.getDate()));
        rateByDates.add(rateToEdit);
    }

    public synchronized void deleteCurrency(String currencyName, LocalDate date) {
        RateByDate rateToEdit = rateByDates.stream()
                .filter(rate -> rate.getDate().isEqual(date))
                .findFirst().orElse(null);
        if (Objects.isNull(rateToEdit)) {
            return;
        }
        rateToEdit.removeCurrency(currencyName);
        rateByDates.removeIf(rate -> rate.getDate().isEqual(rateToEdit.getDate()));
        rateByDates.add(rateToEdit);
    }
}
