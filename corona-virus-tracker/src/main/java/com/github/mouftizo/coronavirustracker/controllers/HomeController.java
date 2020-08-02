package com.github.mouftizo.coronavirustracker.controllers;

import com.github.mouftizo.coronavirustracker.models.LocationStats;
import com.github.mouftizo.coronavirustracker.services.CoronaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    CoronaData locationStats;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = this.locationStats.getAllStats();
        var totalCases = allStats.stream().mapToInt(LocationStats::getLatestTotal).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalCases);
        return "home";
    }
}
