package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;



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


    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<String> jobs = JobData.findAll(searchType);
        ArrayList<String> jobResults = new ArrayList<>();
        for (String jobResult : jobs) {
            if (jobResult.contains(searchTerm)) {
                jobResults.add(jobResult);
            }
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("jobResults", jobResults);

        return "results";
    }
}
