package com.pfe.msmongodb.Controller;

import com.pfe.msmongodb.Models.Dashboard;
import com.pfe.msmongodb.Models.JSONAnswer;
import com.pfe.msmongodb.Repositories.DashboardRepository;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@CrossOrigin
@RequestMapping("/dashboard")
public class DashboardController {


    @Autowired
    DashboardRepository dashboardRepository;


    @PostMapping("/addResult")
    public void insert(@RequestBody Dashboard dashboard) throws JSONException, ParseException {

        dashboardRepository.save(dashboard);
    }


    @GetMapping("/getOneResult/{title}")
    public JSONAnswer oneResult(@PathVariable("title") String title) throws Exception {
        if (!(dashboardRepository.findByTitle(title).isPresent())){
            throw new Exception("not found");
        }else {
            JSONAnswer jsonAnswer = new JSONAnswer(dashboardRepository.findByTitle(title).get().getJsonAnswer());
            return jsonAnswer;
        }
    }
}
