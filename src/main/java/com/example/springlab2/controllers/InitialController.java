package com.example.springlab2.controllers;

import com.example.springlab2.model.RateByDate;
import com.example.springlab2.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

@Controller
@SessionAttributes("role")
public class InitialController {

    @Autowired
    MainService mainService;

    @GetMapping()
    public String getStartPage() {
        return "startPage";
    }

    @PostMapping("/view")
    public String viewCurrencies(HttpSession session,
                                 Model model,
                                 @RequestParam("role") String role) {
        session.setAttribute("isAdmin", mainService.isAdmin(role));
        model.addAttribute("role", role);
        RateByDate todayRate = mainService.getTodayRate();
        model.addAttribute("date", todayRate.getDate());
        model.addAttribute("currencies", todayRate.getCurrencies());
        return "currenciesView";
    }

    @GetMapping("/view")
    public String viewCurrencies(Model model) {
        RateByDate todayRate = mainService.getTodayRate();
        model.addAttribute("date", todayRate.getDate());
        model.addAttribute("currencies", todayRate.getCurrencies());
        return "currenciesView";
    }


    @GetMapping("/searchRates")
    public String searchRates(Model model,
                              @RequestParam("from") String from,
                              @RequestParam("to") String to) throws ParseException {
        Calendar fromDate = mainService.dateToCalendar(from);
        Calendar toDate = mainService.dateToCalendar(to);
        List<RateByDate> rates = mainService.getSpecifiedRates(fromDate, toDate);
        model.addAttribute("rates", rates);
        return "searchedCurrencies";
    }

}
