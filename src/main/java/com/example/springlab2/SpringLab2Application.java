package com.example.springlab2;

import com.example.springlab2.dao.MainDao;
import com.example.springlab2.model.Currency;
import com.example.springlab2.model.CurrencyName;
import com.example.springlab2.services.MainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

@SpringBootApplication
public class SpringLab2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringLab2Application.class, args);
        System.out.println(context.getBean("rate").hashCode());
        System.out.println(context.getBean("rate").hashCode());
        for(String s : context.getBeanDefinitionNames()) {
            System.out.println(s);
        }
        ArrayList<Currency> currencies1 = new ArrayList<>(); 
        currencies1.add(new Currency(CurrencyName.US.toString(), 26.7));
        currencies1.add(new Currency(CurrencyName.EUR.toString(), 30.7));
        currencies1.add(new Currency(CurrencyName.UK.toString(), 36.7));

        context.getBean(MainService.class).addRate(
                LocalDate.now(), currencies1);
        System.out.println(context.getBean(MainDao.class).getRates().size());
    }
}
