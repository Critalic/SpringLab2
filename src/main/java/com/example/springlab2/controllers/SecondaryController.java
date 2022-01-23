package com.example.springlab2.controllers;

import com.example.springlab2.dao.MainDao;
import com.example.springlab2.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class SecondaryController {
    private MainService mainService;

    @Autowired
    public void getMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/view")
    public String viewCurrencies(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if(mainService.isAdmin(role)) return "currenciesView";
        return null;
    }

    @GetMapping("/editRates")
    public String editRates(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if(mainService.isAdmin(role)) return "currenciesView";
        return null;
    }
}
