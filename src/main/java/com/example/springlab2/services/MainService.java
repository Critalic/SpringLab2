package com.example.springlab2.services;

import com.example.springlab2.dao.MainDao;
import com.example.springlab2.model.Currency;
import com.example.springlab2.model.RateByDate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService implements ApplicationContextAware {
    private ApplicationContext context;
    private final MainDao mainDao;

    public MainService(MainDao mainDao) {
        this.mainDao = mainDao;
    }

    public boolean isAdmin(String input) {
        return input.equals("admin");
    }

    public void addRate(LocalDate date, ArrayList<Currency> currencies) {
        RateByDate rate = context.getBean(RateByDate.class);
        rate.setDate(date);
        rate.setCurrencies(currencies);
        mainDao.addRate(rate);
    }

    public RateByDate getTodayRate() {
        return mainDao.getRates().stream()
                .max(new CompareRate())
                .orElse(new RateByDate());
    }

    public List<RateByDate> getSpecifiedRates(LocalDate from, LocalDate to) {
        return mainDao.getRates().stream()
                .filter(rate  -> rate.getDate().isAfter(from) &&
                        rate.getDate().isBefore(to))
                .collect(Collectors.toList());
    }

    public RateByDate getSpecifiedRate(LocalDate on) {
        return mainDao.getRates().stream()
                .filter(rate  -> rate.getDate().equals(on))
                .findFirst().orElse(new RateByDate());
    }

    public LocalDate parseDate(String date) throws ParseException {
        return LocalDate.parse(date);
    }

    public void deleteEntry(String currencyName, LocalDate date) {
        mainDao.deleteCurrency(currencyName, date);
    }

    public void addEntry(String inputName, double inputRate, LocalDate date){
        mainDao.addCurrency(new Currency(inputName, inputRate), date);
    }

    private RateByDate getMostRecentRate() {
        ArrayList<RateByDate> rates = mainDao.getRates();
        rates.sort(new CompareRate());
        return rates.get(0);
    }

    @Bean("rate")
    @Scope("prototype")
    public RateByDate createRate() {
        return new RateByDate();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}

class CompareRate implements Comparator<RateByDate> {

    @Override
    public int compare(RateByDate o1, RateByDate o2) {
        if (o1.getDate().isAfter(o2.getDate())) return 1;
        else if (o1.getDate().isBefore(o2.getDate())) return -1;
        return 0;
    }
}
