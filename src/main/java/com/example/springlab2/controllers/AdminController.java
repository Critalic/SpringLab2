package com.example.springlab2.controllers;

import com.example.springlab2.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
@SessionAttributes("date")
public class AdminController {
    private MainService mainService;
    private InitialController initialController;

    @Autowired
    public void getServices(MainService mainService, InitialController initialController) {
        this.mainService = mainService;
        this.initialController = initialController;
    }

    @GetMapping("/editRates")
    public String editRates(HttpSession session, Model model,
                            @RequestParam("date") String date) {
        String role = (String) session.getAttribute("role");
        if (mainService.isAdmin(role)) {
            LocalDate fromDate = mainService.parseDate(date);
            model.addAttribute("date", date);
            model.addAttribute("currencies", mainService.getSpecifiedRate(fromDate).getCurrencies());
            return "editCurrencies";
        }
        return initialController.viewCurrencies(model);
    }

    @PostMapping("/replace")
    public String replaceCurrency(Model model,
                                  @RequestParam("currencyName") String currencyName,
                                  @RequestParam("inputName") String inputName,
                                  @RequestParam("inputRate") double inputRate) {
        LocalDate calendarDate = mainService.parseDate(
                (String) model.getAttribute("date"));
        mainService.deleteEntry(currencyName, calendarDate);
        mainService.addEntry(inputName, inputRate, calendarDate);
        model.addAttribute("currencies", mainService.getSpecifiedRate(calendarDate).getCurrencies());
        return "editCurrencies";
    }

    @PostMapping("/delete")
    public String deleteCurrency(Model model,
                                 @RequestParam("currencyName") String currencyName) {
        LocalDate calendarDate = mainService.parseDate(
                (String) model.getAttribute("date"));
        mainService.deleteEntry(currencyName, calendarDate);
        model.addAttribute("currencies", mainService.getSpecifiedRate(calendarDate).getCurrencies());
        return "editCurrencies";
    }

    @PostMapping("/add")
    public String addCurrency(Model model,
                              @RequestParam("inputName") String inputName,
                              @RequestParam("inputRate") double inputRate) {
        LocalDate calendarDate = mainService.parseDate(
                (String) model.getAttribute("date"));
        mainService.addEntry(inputName, inputRate, calendarDate);
        model.addAttribute("currencies", mainService.getSpecifiedRate(calendarDate).getCurrencies());
        return "editCurrencies";
    }
}
