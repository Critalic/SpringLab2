package com.example.springlab2.controllers;

import com.example.springlab2.model.RateByDate;
import com.example.springlab2.services.MainService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
        model.addAttribute("date", mainService.getTodayRate().getDate());
        model.addAttribute("currencies", mainService.getTodayRate().getCurrencies());
        return "currenciesView";
    }

    @GetMapping("/view")
    public String viewCurrencies(HttpSession session, Model model) {
        if((Boolean) session.getAttribute("isAdmin")) return "currenciesView&Edit";
        model.addAttribute("date", mainService.getTodayRate().getDate());
        model.addAttribute("currencies", mainService.getTodayRate().getCurrencies());
        return "currenciesView";
    }


    @GetMapping("/searchRates")
    public String searchRates(Model model,
                              @RequestParam("from") String from,
                              @RequestParam("to") String to) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = format.parse(from);
        Date toDate = format.parse(to);
        List<RateByDate> rates = mainService.getSpecifiedRates(fromDate, toDate);
        model.addAttribute("rates", rates);
        return "searchedCurrencies";
    }

}
