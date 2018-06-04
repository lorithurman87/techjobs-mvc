package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        int counter = 0;

        if (searchType.equals("all")) {
            counter = JobData.findByValue(searchType).size();
            String counterNumb = Integer.toString(counter) + " Results";
            model.addAttribute("jobs", JobData.findByValue(searchType));
            model.addAttribute("counter", counter);

        } else {
            counter = JobData.findByColumnAndValue(searchType, searchTerm).size();
            String counterNumb = Integer.toString(counter) + " Results";
            model.addAttribute("jobs", JobData.findByColumnAndValue(searchType, searchTerm));
            model.addAttribute("counter", counter);
        }
        return "search";

    }

}
