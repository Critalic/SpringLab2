package com.example.springlab2;

import com.example.springlab2.dao.MainDao;
import com.example.springlab2.model.Currency;
import com.example.springlab2.model.CurrencyName;
import com.example.springlab2.model.RateByDate;
import com.example.springlab2.services.MainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class SpringLab2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringLab2Application.class, args);

        for(String s : context.getBeanDefinitionNames()) {
            System.out.println(s);
        }
        ArrayList<Currency> currencies1 = new ArrayList<>(); 
        currencies1.add(new Currency(CurrencyName.US, 26.7));
        currencies1.add(new Currency(CurrencyName.EUR, 30.7));
        currencies1.add(new Currency(CurrencyName.UK, 36.7));

//        context.getBean(MainService.class).addRate(
//                new GregorianCalendar(2022, Calendar.JANUARY, 10), currencies1);
        System.out.println(context.getBean(MainDao.class).getRates().size());
    }
}
