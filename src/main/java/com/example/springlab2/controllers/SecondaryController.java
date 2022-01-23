package com.example.springlab2.controllers;

import com.example.springlab2.services.MainService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondaryController {
    private MainService mainService;

    @Autowired
    public void getMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/editRates")
    public String editRates(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if(mainService.isAdmin(role)) return "currenciesView";
        return null;
    }
}
