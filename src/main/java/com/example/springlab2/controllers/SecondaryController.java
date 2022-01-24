package com.example.springlab2.controllers;

import com.example.springlab2.model.RateByDate;
import com.example.springlab2.services.MainService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SecondaryController {
    private MainService mainService;
    private InitialController initialController;

    @Autowired
    public void getServices(MainService mainService, InitialController initialController) {
        this.mainService = mainService;
        this.initialController = initialController;
    }

    @GetMapping("/editRates")
    public String editRates(HttpSession session, Model model,
                            @RequestParam("date") String date) throws ParseException {
        String role = (String) session.getAttribute("role");
        if(mainService.isAdmin(role)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = format.parse(date);
            model.addAttribute("date", date);
            model.addAttribute("currencies", mainService.getSpecifiedRate(fromDate).getCurrencies());
            return "editCurrencies";
        }
        return initialController.viewCurrencies(model);
    }
}
